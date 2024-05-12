package hackbangalore.freelancer.ai.jpa;

import hackbangalore.freelancer.ai.entity.JobUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface JobUserRepository extends JpaRepository<JobUser, Long> {

    List<JobUser> findByUserId(Long userId);

    List<JobUser> findByJobId(Long jobId);
}
