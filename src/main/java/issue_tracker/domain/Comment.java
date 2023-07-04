package issue_tracker.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(
            name = "comment_text",
            nullable = false
    )
    private String commentText;


    @ManyToOne
    @JoinColumn(
            name = "issue_id",
            nullable = false
    )
    private Issue issue;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

}
