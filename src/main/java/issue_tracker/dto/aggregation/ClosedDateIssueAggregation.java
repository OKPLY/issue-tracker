package issue_tracker.dto.aggregation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClosedDateIssueAggregation {
    private LocalDateTime createdAt;
    private Long amount;

    public ClosedDateIssueAggregation(LocalDateTime createdAt, Long amount) {
        this.createdAt = createdAt;
        this.amount = amount;
    }
}
