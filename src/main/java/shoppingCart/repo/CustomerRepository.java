package shoppingCart.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import shoppingCart.model.Customer;
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
