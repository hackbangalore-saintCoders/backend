package hackbangalore.freelancer.ai.service;

import hackbangalore.freelancer.ai.entity.Users;
import hackbangalore.freelancer.ai.exception.ResourceNotFoundException;
import hackbangalore.freelancer.ai.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    // Read
    public Users getUserById(Long id) {
        Optional<Users> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    // Update
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
}

