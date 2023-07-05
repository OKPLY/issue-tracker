package issue_tracker.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
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

    @Column(
            name = "created_at",
            nullable = false
    )
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at",
            nullable = false
    )
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updatedAt;

    @Column(
            name = "deleted",
            nullable = false,
            columnDefinition = "boolean default false"
    )
    @JsonIgnore
    private Boolean deleted = false;


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
    @JsonManagedReference
    private User creator;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    @JsonManagedReference
    private User resolver;

    @ManyToOne
    @JoinColumn(name = "parent_issue_id")
    private Issue parentIssue;

    @ManyToMany
    @JsonManagedReference
    private List<Tag> tags;

    @ManyToOne
    @JsonManagedReference
    private Type type;

}
