package shoppingCart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import shoppingCart.model.Product;
public interface ProductRepository extends JpaRepository<Product, Long>{

}
