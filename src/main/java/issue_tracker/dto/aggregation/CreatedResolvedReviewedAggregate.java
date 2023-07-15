package issue_tracker.dto.aggregation;

import lombok.Data;

@Data
public class CreatedResolvedReviewedAggregate {
    Long created;
    Long resolved;
    Long reviewed;


    // Need for initialization
    public  CreatedResolvedReviewedAggregate() {
        this.created = 0L;
        this.resolved = 0L;
        this.reviewed = 0L;
    }

}

