package issue_tracker.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentDto {

    private Long id;
    private String commentText;
}
