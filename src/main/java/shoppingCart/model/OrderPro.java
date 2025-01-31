package shoppingCart.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class OrderPro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
    @ManyToOne
  @JoinColumn(name = "orderitem_id")
    private OrderItem orderitem;
    private String orderDate;
    private double totalAmount;
    private String status;
    
    
//	public Product getProduct() {
//		return product;
//	}
//	public void setProduct(Product product) {
//		this.product = product;
//	}
    
	public Long getId() {
		return id;
	}
	public OrderItem getOrderitem() {
		return orderitem;
	}
	public void setOrderitem(OrderItem orderitem) {
		this.orderitem = orderitem;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public Customer getCustomer() {
//		return customer;
//	}
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    
}

