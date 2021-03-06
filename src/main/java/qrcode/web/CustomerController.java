package qrcode.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import qrcode.data.entity.Customer;
import qrcode.data.repository.CustomerRepository;
import qrcode.error.CustomerNotFoundException;
import qrcode.error.Error;

@RestController
public class CustomerController {

	CustomerRepository cr;
	
	@Autowired
	public CustomerController(CustomerRepository cr) {
		this.cr = cr;
	}
	
	@RequestMapping(value="customers", method=RequestMethod.GET, produces="application/json")
	public List<Customer> getAllCustomers() {
		System.out.println("GET all customers handler invoked");
		return cr.getCustomers();
	}
	
	@RequestMapping(value = "customer/{id}", method = RequestMethod.GET, produces = "application/json")
	public Customer customerById(@PathVariable long id) {
		System.out.println("GET Request Handler invoked");
		Customer c = cr.findCustomerById(id);
		if (c == null) { throw new CustomerNotFoundException(id);	}
		return c;
	}
	
	@RequestMapping(value={"customers"}, method = RequestMethod.POST, consumes = "application/json"
			                                                         , produces = "application/json" )
	@ResponseStatus(HttpStatus.CREATED)
	
	public Customer createCustomer(@RequestBody Customer c) {
		System.out.println("POST Request Handler invoked");
		c.setId(cr.getNewId());
		cr.addCustomer(c);
		return c;
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) // HttpStatus is an enum
	public Error customerNotFound(CustomerNotFoundException e) {
		long customerId = e.getCustomerId();
		return new Error(404, "Customer [" + customerId + "] not found");
	}
}
