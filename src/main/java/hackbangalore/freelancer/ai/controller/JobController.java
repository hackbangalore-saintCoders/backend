package hackbangalore.freelancer.ai.controller;

import hackbangalore.freelancer.ai.entity.Jobs;
import hackbangalore.freelancer.ai.entity.Users;
import hackbangalore.freelancer.ai.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobsService jobService;

    // Create
    @PostMapping
    public ResponseEntity<Jobs> createJob(@RequestBody Jobs job) {
        Jobs createdJob = jobService.createJob(job);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<Jobs> getJobById(@PathVariable Long id) {
        Jobs job = jobService.getJobById(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Jobs>> getAllJobs() {
        List<Jobs> jobs = jobService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Jobs> updateJob(@PathVariable Long id, @RequestBody Jobs job) {
        Jobs updatedJob = jobService.updateJob(id, job);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get applicants for a job
    @GetMapping("/{jobId}/applicants")
    public List<Users> getApplicantsForJob(@PathVariable Long jobId) {
        return jobService.getApplicantsForJob(jobId);
    }

    // Get jobs applied by a user
    @GetMapping("/user/{userId}/jobs")
    public List<Jobs> getJobsAppliedByUser(@PathVariable Long userId) {
        return jobService.getJobsAppliedByUser(userId);
    }
}
