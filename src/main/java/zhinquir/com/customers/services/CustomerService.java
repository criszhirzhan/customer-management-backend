package zhinquir.com.customers.services;

import zhinquir.com.customers.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {


    public Customer getCustomer ( Integer id);

    public List<Customer> getAllCustomer ();

    public void  addCustomer (Customer customer);
    public void  removeCustomer (Integer id);

    public void  updateCustomer (Integer id,Customer updateCustomer);


    /*busqueda con parametros opcionales*/
    public List<Customer> findCustomer(String email,
                                       String name);

}
