package issue_tracker.dto.comment;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateCommentDto {
    private String CommentText;

    private List<CreateCommentAttachmentDto> attachments;
}

@Getter
@Setter
class CreateCommentAttachmentDto {
    private Long id;
}
