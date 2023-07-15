package issue_tracker.dto.aggregation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResolvedDateIssueAggregation {
    private LocalDateTime resolvedAt;
    private Long amount;

    public ResolvedDateIssueAggregation(LocalDateTime createdAt, Long amount) {
        this.resolvedAt = createdAt;
        this.amount = amount;
    }
}
