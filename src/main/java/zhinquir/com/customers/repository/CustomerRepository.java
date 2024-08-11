package zhinquir.com.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zhinquir.com.customers.entities.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(
            "select c from Customer c where c.firstName like %:email% or c.email like %:name%"
    )
    List<Customer> findByEmailOrFirstName(@Param("email") String email,
                                         @Param("name") String name);
}
