package com.training.demoo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EMPLOYEETRAINING")
public class EmployeeClassEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(min = 2, max = 50)
	@NotBlank(message = "First Name cannot be blank")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotBlank(message = "Last Name cannot be blank")
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Email(message = "Enter a valid email")
	@NotBlank(message = "Email Name cannot be blank")
	@Column(name = "email", nullable = false, length = 200, unique = true)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
