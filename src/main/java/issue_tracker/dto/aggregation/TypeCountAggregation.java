package issue_tracker.dto.aggregation;

import lombok.Data;

@Data
public class TypeCountAggregation {
    private String type;
    private Long count;

    public TypeCountAggregation(String type, Long count) {
        this.type = type;
        this.count = count;
    }
}
