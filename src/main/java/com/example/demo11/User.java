package com.example.demo11;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Table(name = "users")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Component
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)	
	private String userName;
	
	@Column(nullable = false, unique = true)	
	private String phoneNumber;
	
	@Column(nullable = false, unique = true)	
	private String name;	


	public User() {
	}

	public User(String userName, String phoneNumber, String name) {
		super();	
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.name = name;
	}

	public int getID() {
		return id;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User:<" + userName + "," + phoneNumber + "," + name + ">";
	}
}
