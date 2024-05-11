package hackbangalore.freelancer.ai.security.config;

import hackbangalore.freelancer.ai.daoimpl.UserDaoImpl;
import hackbangalore.freelancer.ai.model.UserCred;
import hackbangalore.freelancer.ai.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailService implements UserDetailsService {


    @Autowired
    UserDaoImpl userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserCred> userData = Optional.ofNullable(userDao.findByUserName(username));
        return userData.map(UserDetailObject::new).orElseThrow(()->
                new UsernameNotFoundException("User Doesn't exists"));
    }
}
