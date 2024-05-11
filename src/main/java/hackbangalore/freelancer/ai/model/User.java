//package hackbangalore.freelancer.ai.model;
//
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.UUID;
//
////@Entity
////@Data
//public class User {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    UUID userDetailId;
//
//    @Column(nullable = false)
//    String firstName;
//
//    @Column(nullable = false)
//    String lastName;
//
//    @Column(nullable = false)
//    String phoneNumber;
//
//    byte[] profileImage;
//
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    UserCredential userCredential;
//
//}
