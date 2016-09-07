package com.segolenefatier.project;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
public class User implements Serializable{
	   
	private static final long serialVersionUID = 1L;
	private int id;
	private String lastname;
	private String firstname;
	private String email;
	private String password;
	private String role;
	
	   User(){
		   
	   }
	   
	   User(int id, String lastname, String firstname, String email, String password, String role){
	     this.id = id;
	     this.lastname = lastname;
	     this.firstname = firstname;
	     this.email = email;
	     this.password = password;
	     this.role = role;
	   }

		public int getId() {
			return id;
		}
		 @XmlElement
		public void setId(int id) {
			this.id = id;
		}
	
		public String getLastname() {
			return lastname;
		}
		
		public String getPassword() {
			return password;
		}

		@XmlElement
		public void setPassword(String password) {
			this.password = password;
		}
	
		 @XmlElement
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
	
		public String getFirstname() {
			return firstname;
		}
	
		 @XmlElement
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
	
		public String getEmail() {
			return email;
		}
	
		 @XmlElement
		public void setEmail(String email) {
			this.email = email;
		}
	
		public String getRole() {
			return role;
		}
	
		 @XmlElement
		public void setRole(String role) {
			this.role = role;
		}
	}