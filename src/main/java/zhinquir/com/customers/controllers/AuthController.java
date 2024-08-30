package zhinquir.com.customers.controllers;

import org.springframework.web.bind.annotation.*;
import zhinquir.com.customers.dto.RequestLogin;
import zhinquir.com.customers.entities.User;
import zhinquir.com.customers.services.AuthService;
import zhinquir.com.customers.utils.JwtUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody RequestLogin requestLogin){

        User user = authService.login(requestLogin.getEmail(),
                requestLogin.getPassword());

        String token = JwtUtil.generateToken(user);
        return token;
    }
}
