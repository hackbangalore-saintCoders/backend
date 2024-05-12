package hackbangalore.freelancer.ai.service;

import hackbangalore.freelancer.ai.entity.JobUser;
import hackbangalore.freelancer.ai.entity.Jobs;
import hackbangalore.freelancer.ai.entity.Users;
import hackbangalore.freelancer.ai.exception.ResourceNotFoundException;
import hackbangalore.freelancer.ai.jpa.JobUserRepository;
import hackbangalore.freelancer.ai.jpa.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobsService {

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private JobUserRepository jobUserRepository;

    // Create
    @Transactional
    public Jobs createJob(Jobs job) {
        // Initialize postedOn date
        job.setPostedOn(new Date());
        return jobsRepository.save(job);
    }

    // Read
    public Jobs getJobById(Long id) {
        Optional<Jobs> jobOptional = jobsRepository.findById(id);
        if (jobOptional.isEmpty()) {
            throw new ResourceNotFoundException("Job not found with id: " + id);
        }
        return jobOptional.get();
    }

    public List<Jobs> getAllJobs() {
        return jobsRepository.findAll();
    }

    // Update
    @Transactional
    public Jobs updateJob(Long id, Jobs updatedJob) {
        Jobs existingJob = getJobById(id);
        updatedJob.setId(id); // Ensure the ID matches
        // Keep the postedOn date unchanged
        updatedJob.setPostedOn(existingJob.getPostedOn());
        return jobsRepository.save(updatedJob);
    }

    // Delete
    public void deleteJob(Long id) {
        jobsRepository.deleteById(id);
    }

    // Get applicants for a job
    public List<Users> getApplicantsForJob(Long jobId) {
        return jobUserRepository.findByJobId(jobId).stream()
                .map(JobUser::getUser)
                .collect(Collectors.toList());
    }

    // Get jobs applied by a user
    public List<Jobs> getJobsAppliedByUser(Long userId) {
        return jobUserRepository.findByUserId(userId).stream()
                .map(JobUser::getJob)
                .collect(Collectors.toList());
    }
}
