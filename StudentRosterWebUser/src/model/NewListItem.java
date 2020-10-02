package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class NewListItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int Id;


	@Column(name="FirstName")
	private String firstName;

	@Column(name="LastName")
	private String lastName;

	// no arg constructor
	public NewListItem() {	
		super();
	}

	// constructor to create store and item
	public	NewListItem(String	firstName,	String lastName){
		this.firstName	=	firstName;
		this.lastName	=	lastName;
	}

	public	String	returnItemDetails(	)	{
		return firstName + ": " +	lastName;
	}


	// getters and setters
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "NewListItem [Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	


}
