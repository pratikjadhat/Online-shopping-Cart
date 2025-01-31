package shoppingCart.controller;

import shoppingCart.model.Customer;
import shoppingCart.repo.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController 
{

    @Autowired
    private CustomerRepository customerService;

    @PostMapping("/save")
    public Customer saveCustomer(@RequestBody Customer customer)
    {
    	return customerService.save(customer);
    }
    
    @PostMapping("/saveall")
    public List< Customer> saveAllCustomer(@RequestBody List<Customer> customer)
    {
    	return customerService.saveAll(customer);
    }
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
    	 Optional<Customer> customer = customerService.findById(id);
         return customer.orElse(null); 
    }

    
   
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) 
    {	
    	 if (customerService.existsById(id)) {
             customer.setId(id); 
             return customerService.save(customer);
         }
         return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
    }
}

