package zhinquir.com.customers.services;

import zhinquir.com.customers.entities.User;

import java.util.List;

public interface UserService {
    public User getUser (Integer id);

    public List<User> getAllUser ();

    public void  addUser (User customer);
    public void  removeUser (Integer id);

    public void  updateUser (Integer id,User updateUser);


    /*busqueda con parametros opcionales*/
    public List<User> findUser(String email,
                                       String name);
}
