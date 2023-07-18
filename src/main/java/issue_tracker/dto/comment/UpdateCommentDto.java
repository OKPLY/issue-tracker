package issue_tracker.dto.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import issue_tracker.dto.attachment.AttachmentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateCommentDto {
    @JsonIgnore
    private Long id;
    private String commentText;

    private List<AttachmentDto> attachments;
}

