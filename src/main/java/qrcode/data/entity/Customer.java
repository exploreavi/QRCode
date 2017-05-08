package qrcode.data.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "public.customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cust_id", nullable=false, length=15)
	long id;
	String fName;
	String lName;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="customer_qrcodes", joinColumns= {@JoinColumn(name= "cust_id")}, inverseJoinColumns={@JoinColumn(name = "qr_id")})
	List<QRCode> qrl = new LinkedList<QRCode>();
	
			
	public Customer() {
		fName = "default";
		lName = "default";
	}
	
	public void setId(long customerId) {
		this.id = customerId;
	}

	public long getId() {
		return id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public String toString() {
		return "Customer: " + " id " + this.id + " fName " + this.fName + " lName " + this.lName;
	}
} // end Customer class
