package issue_tracker.dto.issue;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateIssueDto {

    private Long id;

    private String title;

    private String description;

    private List<UpdateIssueTagDto> tags;

    private UpdateIssueTypeDto type;

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