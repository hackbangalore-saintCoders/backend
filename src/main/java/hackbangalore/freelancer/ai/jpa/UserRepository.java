package hackbangalore.freelancer.ai.jpa;

import hackbangalore.freelancer.ai.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserCredential, UUID> {


    UserCredential getUserCredentialByEmail(String email);



}
