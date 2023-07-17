package issue_tracker.dto.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateCommentDto {
    @JsonIgnore
    private Long id;
    private String commentText;

    private List<UpdateCommentAttachmentDto> attachments;
}

@Getter
@Setter
class UpdateCommentAttachmentDto {
    private Long id;
}
