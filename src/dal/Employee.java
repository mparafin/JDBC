package dal;

import java.time.LocalDate;

public class Employee {

	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private LocalDate hireDate;
	private String jobId;
	private int salary;
	private int managerId;
	private int departmentId;
	
	public Employee() {}
	public Employee(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public String getJobId() {
		return jobId;
	}

	public int getSalary() {
		return salary;
	}

	public int getManagerId() {
		return managerId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setHireDate(LocalDate localDate) {
		this.hireDate = localDate;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
}
