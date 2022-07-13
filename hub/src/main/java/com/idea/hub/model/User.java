package com.idea.hub.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint; 



@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String password;
	private String DOB;
	private String email;
	private Long mobile;
	private String roles;
	//private String token;
	
	/*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id")
			)
	
	private Collection<Role> utype;*/
	
	
	public User() {
		super();
	}
	public User(String name, String password, String dOB, String email, long mobile, String roles) {
		super();
		this.name = name;
		this.password = password;
		DOB = dOB;
		this.email = email;
		this.mobile = mobile;
		this.roles=roles;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	/*public Collection<Role> getUtype() {
		return utype;
	}
	public void setUtype(Collection<Role> utype) {
		this.utype = utype;
	}*/
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	/*public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	*/
	
	
	
	
	
}
