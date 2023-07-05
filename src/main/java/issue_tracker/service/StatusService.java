package issue_tracker.service;

import issue_tracker.domain.Status;
import issue_tracker.dto.status.CreateStatusDto;
import issue_tracker.dto.status.UpdateStatusDto;

import java.util.List;

public interface StatusService {
    List<Status> findAll();

    Status findById(Long id);

    Status create(CreateStatusDto statusDto);

    Status update(UpdateStatusDto statusDto);

    Status delete(Long id);
}
