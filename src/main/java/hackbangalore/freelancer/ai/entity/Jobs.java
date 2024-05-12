package hackbangalore.freelancer.ai.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "jobs")
@Data
public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "skills", nullable = false)
    private String skills;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "budget", nullable = false)
    private BigDecimal budget;

    @Column(name = "posted_on", nullable = false)
    private Date postedOn;

    @ManyToMany
    @JoinTable(
            name = "job_user",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Users> applicants;

    // Constructors, getters, and setters
    // Omitted for brevity
}
