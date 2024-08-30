package zhinquir.com.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zhinquir.com.customers.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(
            "select c from User c where c.firstName like %:name% or c.email like %:email%"
    )
    List<User> findByEmailOrFirstName(@Param("email") String email,
                                      @Param("name") String name);

    @Query(
            "select c from User c where c.email = :email and c.password = :password"
    )
    List<User> findByEmailAndPassword(@Param("email") String email,
                                      @Param("password") String password);

}
