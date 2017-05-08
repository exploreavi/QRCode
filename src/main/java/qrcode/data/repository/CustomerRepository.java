package qrcode.data.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import qrcode.data.entity.Customer;

@Repository
public class CustomerRepository {
	List<Customer> cl;
	long id;
	
	public CustomerRepository() {
		cl = new LinkedList<Customer>();
	}

	public long getNewId() {
		id++; // random? or hash
		return id;
	}
	public boolean addCustomer(Customer c) {
		cl.add(c);
		return true;
	}

	public boolean removeCustomer(long id) {
		Customer c = findCustomerById(id);
		if (c != null) {
			cl.remove(c);
			return true;
		}
		return false;
	}

	public Customer findCustomerById(long id) {
		for (Customer c : cl) {
			if (c.getId() == id)
				return c;
		}
		return null;
	}
	
	public List<Customer> getCustomers() {
		return cl;
	}
}