package hackbangalore.freelancer.ai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID userId;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String pwd;

    @OneToOne(mappedBy = "userCredential", cascade = CascadeType.ALL)
    User user;

    @OneToOne(mappedBy = "userCredential", cascade = CascadeType.ALL)
    DateObject dateObject;


    public UserCredential(String email, String pwd){
        this.email = email;
        this.pwd = pwd;
    }


}
