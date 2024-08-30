package zhinquir.com.customers.services;

import zhinquir.com.customers.entities.User;

public interface AuthService {
    public User login(String email, String password);
}
