package issue_tracker.service.impl;

import issue_tracker.domain.Status;
import issue_tracker.dto.status.CreateStatusDto;
import issue_tracker.dto.status.UpdateStatusDto;
import issue_tracker.repository.StatusRepo;
import issue_tracker.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepo statusRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<Status> findAll() {
        return statusRepo.findAll();
    }

    @Override
    public Status findById(Long id) {
        var res = statusRepo.findById(id);

        if (res.isPresent())
            return res.get();

        throw new NoSuchElementException("Status Not Found");
    }

    @Override
    public Status create(CreateStatusDto statusDto) {
        var status = modelMapper.map(statusDto, Status.class);

        Status saved;

        try {
            saved = statusRepo.save(status);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Status With The Same Name Already Exists.");
        }

        return saved;
    }

    @Override
    public Status update(UpdateStatusDto statusDto) {
        var status = findById(statusDto.getId());
        modelMapper.map(statusDto, status);

        Status saved;

        try {
            saved = statusRepo.save(status);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Status With The Same Name Already Exists.");
        }

        return saved;
    }

    @Override
    public Status delete(Long id) {
        var status = findById(id);
        status.setDeleted(true);

        return statusRepo.save(status);
    }
}
