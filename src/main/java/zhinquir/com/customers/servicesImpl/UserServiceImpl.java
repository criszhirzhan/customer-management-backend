package zhinquir.com.customers.servicesImpl;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhinquir.com.customers.entities.User;
import zhinquir.com.customers.entities.User;
import zhinquir.com.customers.repository.UserRepository;
import zhinquir.com.customers.repository.UserRepository;
import zhinquir.com.customers.services.UserService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String SECRETER_KEY = "ge6238";
    @Autowired
    private UserRepository repository;

    public UserServiceImpl(){


    }

    public User getUser ( Integer id){

        Optional<User> user = repository.findById(id);
        return user.orElse(null);
    }

    public List<User>  getAllUser (){
        return repository.findAll();
    }

    public void  addUser (User user){

        // crea el hash de la password, es irreversible, se puede tambien agregar una palabra secreta
        String hashPassword = Hashing.sha256()
                .hashString(user.getPassword() + SECRETER_KEY, StandardCharsets.UTF_8)
                .toString();

        user.setPassword(hashPassword);

        repository.save(user);
    }

    public void  removeUser (Integer id){
        repository.deleteById(id);
    }

    public void  updateUser (Integer id,User updateUser){
        updateUser.setId(id);
        repository.save(updateUser);
    }


    /*busqueda con parametros opcionales*/
    public List<User> findUser(String email,
                                       String address){

        return repository.findByEmailOrFirstName(email, address);
    }
}
