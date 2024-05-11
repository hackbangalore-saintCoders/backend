package hackbangalore.freelancer.ai.dao;

import hackbangalore.freelancer.ai.model.User;
import hackbangalore.freelancer.ai.model.UserCred;
import hackbangalore.freelancer.ai.model.UserCredential;

import java.util.UUID;

public interface UserDao {

    boolean createUserCredentials(UserCredential userCredential);

    boolean loginUser(String email, String pwd);

    boolean userExists(String email);

    UserCred findByUserName(String email);


    UUID getUUIDFromEmail(String email);

}
