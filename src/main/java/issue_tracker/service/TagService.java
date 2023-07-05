package issue_tracker.service;

import issue_tracker.domain.Tag;
import issue_tracker.dto.tag.CreateTagDto;
import issue_tracker.dto.tag.UpdateTagDto;

import java.util.List;

public interface TagService {
    List<Tag> findAll();

    Tag findById(Long id);

    Tag create(CreateTagDto tagDto);

    Tag update(UpdateTagDto tagDto);

    Tag delete(Long id);
}
