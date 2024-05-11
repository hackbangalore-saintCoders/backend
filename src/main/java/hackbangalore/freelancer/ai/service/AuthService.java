package hackbangalore.freelancer.ai.service;


import hackbangalore.freelancer.ai.daoimpl.UserDaoImpl;
import hackbangalore.freelancer.ai.model.UserCredential;
import hackbangalore.freelancer.ai.model.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {


    @Autowired
    UserDaoImpl userDao;

    @Autowired
    PasswordEncoder passwordEncoder;


    public boolean createUserCredential(UserRegistrationRequest userRegistrationRequest){

        if(!checkPwdTwice(userRegistrationRequest.getPwd(), userRegistrationRequest.getConfirmPwd())){
            return false;
        }

       return userDao.createUserCredentials(new UserCredential(userRegistrationRequest.getEmail(),passwordEncoder.encode(userRegistrationRequest.getConfirmPwd())));

    }

    private boolean checkPwdTwice(String pwd1, String pwd2){
        return pwd1.equals(pwd2);
    }


    public UUID getUUIDFromEmail(String email){
        return userDao.getUUIDFromEmail(email);
    }


}
