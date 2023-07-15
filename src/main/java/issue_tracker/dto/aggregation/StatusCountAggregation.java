package issue_tracker.dto.aggregation;

import issue_tracker.domain.Status;
import lombok.Data;

@Data
public class StatusCountAggregation {
    private Status status;
    private Long count;

    public StatusCountAggregation(Status status, Long count) {
        this.status = status;
        this.count = count;
    }
}
