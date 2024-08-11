package zhinquir.com.customers.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhinquir.com.customers.entities.Customer;
import zhinquir.com.customers.repository.CustomerRepository;
import zhinquir.com.customers.services.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private List<Customer> customers = new ArrayList<>();
    @Autowired
    private CustomerRepository repository;

    public CustomerServiceImpl(){
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("Maria");
        customer1.setLastName("Paz");
        customer1.setEmail("maria@gmail.com");
        customer1.setPhone("0986348567");

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("Chris");
        customer2.setLastName("Cabrera");
        customer2.setEmail("cabrera@gmail.com");
        customer2.setPhone("0986348567");

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setFirstName("Pedro");
        customer3.setLastName("Gomez");
        customer3.setEmail("pedro@gmail.com");
        customer3.setPhone("0986348567");

        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

    }

    public Customer getCustomer ( Integer id){

       Optional<Customer> customer = repository.findById(id);
        return customer.orElse(null);
    }

    public List<Customer>  getAllCustomer (){
        return repository.findAll();
    }

    public void  addCustomer (Customer customer){
        repository.save(customer);
    }

    public void  removeCustomer (Integer id){
        repository.deleteById(id);
    }

    public void  updateCustomer (Integer id,Customer updateCustomer){
        updateCustomer.setId(id);
        repository.save(updateCustomer);
    }


    /*busqueda con parametros opcionales*/
    public List<Customer> findCustomer(String email,
                                       String address){

        return repository.findByEmailOrFirstName(email, address);
    }

}
