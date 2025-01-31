package shoppingCart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import shoppingCart.model.OrderPro;
public interface OrderRepository extends JpaRepository<OrderPro, Long>{

}
