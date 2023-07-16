package issue_tracker.dto.aggregation;

import lombok.Data;

@Data
public class TagCountAggregation {

    private String tagName;
    private Long count;


    public TagCountAggregation(String tagName, Long count) {
        this.tagName = tagName;
        this.count = count;
    }

}
