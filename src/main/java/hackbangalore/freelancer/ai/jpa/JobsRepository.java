package hackbangalore.freelancer.ai.jpa;

import hackbangalore.freelancer.ai.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepository extends JpaRepository<Jobs, Long> {
    // You can add custom query methods here if needed
}