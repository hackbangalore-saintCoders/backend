package hackbangalore.freelancer.ai.entity;

import jakarta.persistence.*;
import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class Users  //implements UserDetails {
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "roles", length = 45)
    private String roles;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "language", length = 100)
    private String language;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "skill", columnDefinition = "TEXT")
    private String skill;

    @Column(name = "rating")
    private Double rating;

    @Lob
    @Column(name = "profile_pic")
    private byte[] profilePic;

    @Lob
    @Column(name = "resume")
    private byte[] resume;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @ManyToMany(mappedBy = "applicants")
    private List<Jobs> appliedJobs;

}
