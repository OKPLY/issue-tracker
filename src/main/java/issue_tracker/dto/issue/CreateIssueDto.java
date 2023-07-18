package issue_tracker.dto.issue;

import issue_tracker.domain.Attachment;
import issue_tracker.dto.attachment.AttachmentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateIssueDto {
    private String title;

    private String description;

    private List<CreateIssueTagDto> tags;

    private CreateIssueTypeDto type;

    private List<AttachmentDto> attachments;

}

@Getter
@Setter
class CreateIssueTypeDto {
    private Long id;
}

@Getter
@Setter
class CreateIssueTagDto {
    private Long id;
}
