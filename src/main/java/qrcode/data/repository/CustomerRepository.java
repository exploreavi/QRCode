package qrcode.data.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qrcode.data.entity.Customer;
import qrcode.data.entity.QRCode;

@Repository
public class CustomerRepository {

	SessionFactory sf;

	List<Customer> cl;
	long id;

	@Autowired
	public CustomerRepository(SessionFactory sf) {
		this.sf = sf;
		System.out.println("LSB: " + sf.toString());
		cl = new LinkedList<Customer>();
	}

	public CustomerRepository() {
	}

	public long getNewId() {
		id++; // random? or hash
		return id;
	}

	public boolean addCustomer(Customer c, QRCode qr) {
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		s.save(c);
		s.save(qr);
		s.flush();
		try {
			t.commit();
		} catch (Exception e) {
			System.out.println("Commit failed, RollBack now");
			t.rollback();
		} finally {
			System.out.println("Closing Session");
			s.close();
		}

		cl.add(c);
		return true;
	}

	public boolean removeCustomer(long id) throws IOException {
		Customer c = findCustomerById(id);
		if (c != null) {
			cl.remove(c);
			return true;
		}
		return false;
	}

	public Customer findCustomerById(long id) throws IOException {
		Session s = sf.openSession();
		QRCode qrimage = (QRCode) s.get(QRCode.class, id);
		File f = new File("qr1.png");

		FileOutputStream fo = new FileOutputStream(f);
		fo.write(qrimage.getQrCode());
		fo.flush();
		fo.close();

		// Customer c1 = (Customer) s.get(Customer.class, id);
		s.close();
		return null;

		// for (Customer c : cl) {
		// if (c.getId() == id)
		// return c;
		// }
		// return null;
	}

	public List<Customer> getCustomers() {
		return cl;
	}
}