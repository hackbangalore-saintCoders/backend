//package hackbangalore.freelancer.ai.daoimpl;
//
//import hackbangalore.freelancer.ai.dao.UserDao;
//import hackbangalore.freelancer.ai.jpa.UserRepository;
//import hackbangalore.freelancer.ai.model.UserCred;
//import hackbangalore.freelancer.ai.model.UserCredential;
//import jakarta.transaction.Transactional;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
////@Component
////@Slf4j
//public class UserDaoImpl implements UserDao {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Transactional
//    @Override
//    public boolean createUserCredentials(UserCredential userCredential) {
//
//        try {
//            userRepository.save(userCredential);
//            return true;
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean loginUser(String email, String pwd) {
//        return false;
//    }
//
//    @Override
//    public boolean userExists(String email) {
//        return false;
//    }
//
//    @Override
//    public UserCred findByUserName(String email) {
//        UserCredential userCredential = userRepository.getUserCredentialByEmail(email);
//        return new UserCred(userCredential.getEmail(),userCredential.getPwd());
//    }
//
//    @Override
//    public UUID getUUIDFromEmail(String email) {
//        return userRepository.getUserCredentialByEmail(email).getUserId();
//    }
//}
