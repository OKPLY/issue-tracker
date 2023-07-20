package issue_tracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Where(clause = "deleted=false")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "comment_text",
            nullable = false
    )
    private String commentText;

    @OneToMany
    @JoinColumn(name = "comment_id")
    @Cascade(CascadeType.ALL)
    private List<Attachment> attachments;

    @Column(
            name = "created_at",
            nullable = false
    )
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at",
            nullable = false
    )
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(
            name = "deleted",
            nullable = false,
            columnDefinition = "boolean default false"
    )
    @JsonIgnore
    private Boolean deleted = false;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "issue_id"//,
            //nullable = false
    )
    private Issue issue;

    @ManyToOne
    @JoinColumn(
            name = "user_id"//,
            //nullable = false
    )
    private User user;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

}
