package shoppingCart.controller;

import shoppingCart.model.Customer;
import shoppingCart.model.OrderItem;
import shoppingCart.model.OrderPro;
import shoppingCart.model.Product;
import shoppingCart.repo.CustomerRepository;
import shoppingCart.repo.OrderItemRepository;
import shoppingCart.repo.OrderRepository;
import shoppingCart.repo.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
    private OrderRepository orderService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/create")
    public OrderPro createOrderPro(@RequestBody OrderPro orderPro) {
        // Fetch the OrderItem by ID (the ID is passed in the request)
        OrderItem orderItem = orderItemRepository.findById(orderPro.getOrderitem().getId()).orElse(null);

        if (orderItem != null) {
            // Fetch the related Customer and Product for the OrderItem
            // Ensure that customer and product are properly set in the OrderItem
            if (orderItem.getCustomer() == null || orderItem.getProduct() == null) {
                // If customer or product are not set, fetch them from the database
            	orderItem.setCustomer(customerRepository.findById(orderItem.getCustomer().getId()).orElse(null));
                orderItem.setProduct(productRepository.findById(orderItem.getProduct().getId()).orElse(null));
              }

            // Ensure customer and product are not null
            if (orderItem.getCustomer() != null && orderItem.getProduct() != null) {
                // Set the OrderItem into OrderPro
                orderPro.setOrderitem(orderItem);
//                orderPro.setCustomer(a);
//                orderPro.setProduct(b.getId());
//                 Calculate the total amount: price * quantity
                orderPro.setTotalAmount(orderItem.getPrice());
                
             
                return orderService.save(orderPro);
            } else {
                throw new IllegalStateException("Customer or Product is not set correctly in OrderItem");
            }
        } else {
            throw new IllegalStateException("OrderItem with ID " + orderPro.getOrderitem().getId() + " does not exist");
        }
    }
    	
    // Get order by ID
    @GetMapping("/{id}")
    public OrderPro getOrderById(@PathVariable Long id) {
    	 Optional<OrderPro> order = orderService.findById(id);
         return order.orElse(null);
    }
   
    // Get all orders
    @GetMapping
    public List<OrderPro> getAllOrders() {
        return orderService.findAll();
    }
    

    // Update an existing order
    @PutMapping("/{id}")
    public OrderPro updateOrder(@PathVariable Long id, @RequestBody OrderPro order) {
    	 if (orderService.existsById(id)) {
             order.setId(id);
             return orderService.save(order);
         }
         return null;
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
    }
}

