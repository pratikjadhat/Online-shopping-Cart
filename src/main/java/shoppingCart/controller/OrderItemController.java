package shoppingCart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import shoppingCart.model.Customer;
import shoppingCart.model.OrderItem;
import shoppingCart.model.Product;
import shoppingCart.repo.CustomerRepository;
import shoppingCart.repo.OrderItemRepository;
import shoppingCart.repo.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

	@Autowired
   private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderItemRepository orderItemService;
    
    // Create a new OrderItem
    @PostMapping("/add")
    public ResponseEntity<OrderItem> addOrderItem(@RequestBody OrderItem orderItem) {

        // Fetch the Product and Customer from the database using their IDs
        Product product = productRepository.findById(orderItem.getProduct().getId()).orElse(null);
        Customer customer = customerRepository.findById(orderItem.getCustomer().getId() ).orElse(null);

        if (product != null && customer != null) {
            // Calculate the price (product price * quantity)
            double totalPrice = product.getPrice() * orderItem.getQuantity();

            // Set the product, customer, and calculated price
            orderItem.setProduct(product);
            orderItem.setCustomer(customer);
            orderItem.setPrice(totalPrice);

            // Save the orderItem
            orderItemService.save(orderItem);

            // Return the OrderItem along with Customer details
            return ResponseEntity.ok(orderItem);
        } else {
            return ResponseEntity.status(400).body(null);  // Return 400 if product or customer is not found
        }
    }

    // Get all OrderItems
    @GetMapping("/")
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.findAll();
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    // Get an OrderItem by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        Optional<OrderItem> orderItem = orderItemService.findById(id);
        if (orderItem.isPresent()) {
            return new ResponseEntity<>(orderItem.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing OrderItem
    @PutMapping("/{id}")
    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        return orderItemService.findById(id).map(orderItem -> {
            orderItem.setProduct(updatedOrderItem.getProduct());
            orderItem.setQuantity(updatedOrderItem.getQuantity());
            orderItem.setPrice(updatedOrderItem.getPrice());
            orderItem.setCustomer(updatedOrderItem.getCustomer());
            return orderItemService.save(orderItem);
        }).orElseThrow(() -> new RuntimeException("OrderItem not found"));
    }


    // Delete an OrderItem by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

