package hackbangalore.freelancer.ai.jpa;

import hackbangalore.freelancer.ai.entity.Jobs;
import hackbangalore.freelancer.ai.entity.Users;
//import hackbangalore.freelancer.ai.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

//@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    // Find list of jobs applied by a user
    List<Jobs> findJobsAppliedByUserId(Long userId);

    // Find list of users who have applied for a specific job
//    @Query("SELECT u FROM Users u JOIN u.appliedJobs j WHERE j.id = :jobId")
//    @Query("SELECT u FROM Users u JOIN u.jobsApplied j WHERE j.id = :jobId")
    @Query("SELECT u FROM Users u JOIN u.appliedJobs j WHERE j.id = :jobId")
    List<Users> findUsersAppliedForJobId(Long jobId);
}
