package shoppingCart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import shoppingCart.model.OrderItem;
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
