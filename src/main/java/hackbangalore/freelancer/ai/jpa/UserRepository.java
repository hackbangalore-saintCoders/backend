package hackbangalore.freelancer.ai.jpa;

import hackbangalore.freelancer.ai.entity.Users;
//import hackbangalore.freelancer.ai.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//@Repository
public interface UserRepository extends JpaRepository<Users, Long> {


//    UserCredential getUserCredentialByEmail(String email);
    Users findByEmail(String email);



}
