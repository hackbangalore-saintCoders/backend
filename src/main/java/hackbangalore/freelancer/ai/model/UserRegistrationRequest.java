package hackbangalore.freelancer.ai.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserRegistrationRequest {

    String email;
    String pwd;
    String confirmPwd;
}
