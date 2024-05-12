package hackbangalore.freelancer.ai.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "job_user")
@Data
public class JobUser {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @EmbeddedId
    private JobUserId id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Jobs job;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    // Constructors, getters, and setters
}
