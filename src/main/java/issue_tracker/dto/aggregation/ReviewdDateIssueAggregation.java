package issue_tracker.dto.aggregation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewdDateIssueAggregation {
    private LocalDateTime reviewedAt;
    private Long amount;

    public ReviewdDateIssueAggregation(LocalDateTime createdAt, Long amount) {
        this.reviewedAt = createdAt;
        this.amount = amount;
    }
}

