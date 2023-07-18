package issue_tracker.dto.issue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import issue_tracker.dto.attachment.AttachmentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateIssueDto {

    @JsonIgnore
    private Long id;

    private String title;

    private String description;

    private List<UpdateIssueTagDto> tags;

    private UpdateIssueTypeDto type;

    private List<AttachmentDto> attachments;

}

@Getter
@Setter
class UpdateIssueTypeDto {
    private Long id;
}

@Getter
@Setter
class UpdateIssueTagDto {
    private Long id;
}
