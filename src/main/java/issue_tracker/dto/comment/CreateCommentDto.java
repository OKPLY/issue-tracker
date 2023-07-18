package issue_tracker.dto.comment;

import issue_tracker.dto.attachment.AttachmentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateCommentDto {
    private String CommentText;

    private List<AttachmentDto> attachments;
}

