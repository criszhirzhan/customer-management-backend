package zhinquir.com.customers.controllers;

import org.springframework.web.bind.annotation.*;
import zhinquir.com.customers.entities.User;
import zhinquir.com.customers.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    private UserService service;

    private  UserController(UserService service){
        this.service = service;
    }


    @GetMapping("/user/{id}")
    public User getUser (@PathVariable Integer id){

        return service.getUser(id);
    }

    @GetMapping("/getAllusers")
    public List<User> getAllUser (){

        return service.getAllUser();
    }

    @PostMapping("/addUser")
    public void  registerUser (@RequestBody User user){
        service.addUser(user);
    }

    @DeleteMapping("/removeUser/{id}")
    public void  removeUser (@PathVariable Integer id){
        service.removeUser(id);
    }

    @PutMapping("/updateUser/{id}")
    public void  updateUser (@PathVariable Integer id,@RequestBody User updateUser){
        service.updateUser(id, updateUser);

    }


    /*busqueda con parametros opcionales*/
    @GetMapping("/findUser")
    public List<User> findUser(@RequestParam(name = "email", required = false) String email,
                                       @RequestParam(name = "name", required = false) String name){
        return service.findUser(email, name);
    }
}
