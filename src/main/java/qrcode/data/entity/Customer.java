package qrcode.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	long id;
	String fName;
	String lName;
	
	public Customer() {
		id = -1;
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
} // end Customer class
