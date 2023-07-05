package issue_tracker.dto.issue;

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