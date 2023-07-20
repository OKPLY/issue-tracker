package issue_tracker.service.impl;

import issue_tracker.domain.Tag;
import issue_tracker.dto.tag.CreateTagDto;
import issue_tracker.dto.tag.UpdateTagDto;
import issue_tracker.repository.TagRepo;
import issue_tracker.service.TagService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepo tagRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<Tag> findAll() {
        return tagRepo.findAllByDeletedIsFalse();
    }

    @Override
    public Tag findById(Long id) {
        var res = tagRepo.findAllByIdAndDeletedIsFalse(id);

        if (res.isPresent())
            return res.get();

        throw new NoSuchElementException("Tag Not Found");
    }

    @Override
    public Tag create(CreateTagDto tagDto) {

        var existing = tagRepo.findAllByName(tagDto.getName());
        if(existing.isPresent()){
            existing.get().setDeleted(false);
            return tagRepo.save(existing.get());
        }

        var tag = modelMapper.map(tagDto, Tag.class);

        Tag saved;

        try {
            saved = tagRepo.save(tag);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Tag With The Same Name Already Exists.");
        }

        return saved;
    }

    @Override
    public Tag update(UpdateTagDto tagDto) {
        var tag = findById(tagDto.getId());
        modelMapper.map(tagDto, tag);

        Tag saved;

        try {
            saved = tagRepo.save(tag);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Tag With The Same Name Already Exists.");
        }

        return saved;
    }

    @Override
    public Tag delete(Long id) {
        var tag = findById(id);
        tag.setDeleted(true);

        return tagRepo.save(tag);
    }
}
