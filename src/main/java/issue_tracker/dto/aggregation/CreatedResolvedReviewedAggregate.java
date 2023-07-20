package issue_tracker.dto.aggregation;

import lombok.Data;

@Data
public class CreatedResolvedReviewedAggregate {
    Long created;
    Long resolved;
    Long reviewed;
    Long closed;


    // Need for initialization
    public  CreatedResolvedReviewedAggregate() {
        this.created = 0L;
        this.resolved = 0L;
        this.reviewed = 0L;
        this.closed = 0L;
    }

    public CreatedResolvedReviewedAggregate(Long created, Long resolved, Long reviewed, Long closed) {
        this.created = created;
        this.resolved = resolved;
        this.reviewed = reviewed;
        this.closed =  closed;
    }
}

