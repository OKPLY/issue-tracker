package issue_tracker.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.util.List;

@Data
@Entity
@Where(clause = "deleted=false")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            name = "title",
            nullable = false
    )
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(
            name = "status"//,
            //nullable = false
    )
    private Status status;

    @ManyToOne
    @JoinColumn(
            name = "created_by"//,
            //nullable = false
    )
    @JsonBackReference
    private User creator;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    @JsonBackReference
    private User resolver;

    @ManyToOne
    @JoinColumn(name = "parent_issue_id")
    private Comment parentIssue;

    @ManyToMany
    @JsonManagedReference
    private List<Tag> tags;

    @ManyToOne
    @JsonManagedReference
    private Type type;

    @JsonIgnore
    private boolean deleted = false;

}
