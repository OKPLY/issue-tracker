package issue_tracker.dto.aggregation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
public class CreatedDateIssueAggregation {
    private LocalDateTime createdAt;
    private Long amount;

    public CreatedDateIssueAggregation(LocalDateTime createdAt, Long amount) {
        this.createdAt = createdAt;
        this.amount = amount;
    }
}
