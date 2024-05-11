package hackbangalore.freelancer.ai.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Entity
@Data
public class DateObject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID dateId;

    Date doj;

    Date lastActive;

    @OneToOne
    @JoinColumn(name = "user_id")
    UserCredential userCredential;

}
