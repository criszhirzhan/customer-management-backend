package zhinquir.com.customers.servicesImpl;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;
import zhinquir.com.customers.entities.User;
import zhinquir.com.customers.repository.UserRepository;
import zhinquir.com.customers.services.AuthService;
import zhinquir.com.customers.services.UserService;

import java.lang.invoke.CallSite;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
public class AuthServiceImpl implements AuthService {
    private static final String SECRETER_KEY = "ge6238";

    private final UserService userService;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public User login(String email, String password) {
        String hashPassword = Hashing.sha256()
                .hashString(password + SECRETER_KEY, StandardCharsets.UTF_8)
                .toString();

        List<User> result = userRepository.findByEmailAndPassword(email, hashPassword);

        if(result.isEmpty()){
            return null;
        }else{
            return result.get(0);
        }

    }
}
