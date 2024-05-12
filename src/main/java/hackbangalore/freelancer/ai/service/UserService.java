package hackbangalore.freelancer.ai.service;

import hackbangalore.freelancer.ai.entity.JobUser;
import hackbangalore.freelancer.ai.entity.Jobs;
import hackbangalore.freelancer.ai.entity.Users;
import hackbangalore.freelancer.ai.exception.ResourceNotFoundException;
import hackbangalore.freelancer.ai.jpa.JobUserRepository;
import hackbangalore.freelancer.ai.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobUserRepository jobUserRepository;

    // Create
    @Transactional
    public Users createUser(Users user) {
        System.out.println("In Service : "+ user);
        return userRepository.save(user);
    }

    // Read
    public Users getUserById(Long id) {
        Optional<Users> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) throw new ResourceNotFoundException("User not found with id :" + id);
        return userOptional.get();
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    // Update
    @Transactional
    public Users updateUser(Long id, Users updatedUser) {
        Optional<Users> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            updatedUser.setId(id); // Ensure the ID matches
            return userRepository.save(updatedUser);
        }
//        return null; // Or throw exception if not found
        else
            throw new ResourceNotFoundException("No user found with id : " + id);
    }

    // Delete
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Get list of jobs applied by a user
    public List<Jobs> getAppliedJobsByUser(Long userId) {
        List<JobUser> jobUserList = jobUserRepository.findByUserId(userId);
        return jobUserList.stream().map(JobUser::getJob).collect(Collectors.toList());
    }

    // Get list of users who have applied for a specific job
    public List<Users> getUsersAppliedForJob(Long jobId) {
        List<JobUser> jobUserList = jobUserRepository.findByJobId(jobId);
        return jobUserList.stream().map(JobUser::getUser).collect(Collectors.toList());
    }
}

