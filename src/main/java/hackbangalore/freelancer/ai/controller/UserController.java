package hackbangalore.freelancer.ai.controller;

import hackbangalore.freelancer.ai.entity.Jobs;
import hackbangalore.freelancer.ai.entity.Users;
import hackbangalore.freelancer.ai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create
    @PostMapping
    public Users createUser(@RequestBody Users user) {
        System.out.println(user.toString());
        return userService.createUser(user);
    }

    // Read
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    // Update
    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
        return userService.updateUser(id, user);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Get list of jobs applied by a user
    @GetMapping("/{userId}/applied-jobs")
    public List<Jobs> getAppliedJobsByUser(@PathVariable Long userId) {
        return userService.getAppliedJobsByUser(userId);
    }

    // Get list of users who have applied for a specific job
    @GetMapping("/applied-users/{jobId}")
    public List<Users> getUsersAppliedForJob(@PathVariable Long jobId) {
        return userService.getUsersAppliedForJob(jobId);
    }
}
