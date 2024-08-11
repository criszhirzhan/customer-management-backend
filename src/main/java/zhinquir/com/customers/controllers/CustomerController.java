package zhinquir.com.customers.controllers;

import org.springframework.web.bind.annotation.*;
import zhinquir.com.customers.entities.Customer;
import zhinquir.com.customers.services.CustomerService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    private CustomerService service;

    private  CustomerController(CustomerService service){
        this.service = service;
    }


    @GetMapping("/customer/{id}")
    public Customer getCustomer (@PathVariable Integer id){

        return service.getCustomer(id);
    }

    @GetMapping("/getAllcustomers")
    public List<Customer>  getAllCustomer (){

        return service.getAllCustomer();
    }

    @PostMapping("/addCustomer")
    public void  addCustomer (@RequestBody Customer customer){
        service.addCustomer(customer);
    }

    @DeleteMapping("/removeCustomer/{id}")
    public void  removeCustomer (@PathVariable Integer id){
        service.removeCustomer(id);
    }

    @PutMapping("/updateCustomer/{id}")
    public void  updateCustomer (@PathVariable Integer id,@RequestBody Customer updateCustomer){
        service.updateCustomer(id, updateCustomer);

    }


    /*busqueda con parametros opcionales*/
    @GetMapping("/findCustomer")
   public List<Customer> findCustomer(@RequestParam(name = "email", required = false) String email,
                                      @RequestParam(name = "name", required = false) String name){
        return service.findCustomer(email, name);
    }

}
