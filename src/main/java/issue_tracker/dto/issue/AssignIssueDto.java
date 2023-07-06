package issue_tracker.dto.issue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignIssueDto {

    private Long id;

    private AssignIssueResolver resolver;

}

@Getter
@Setter
class AssignIssueResolver {

    private Long id;

}
