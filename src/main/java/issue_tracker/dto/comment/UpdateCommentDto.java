package issue_tracker.dto.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentDto {

    @JsonIgnore
    private Long id;
    private String commentText;
}
