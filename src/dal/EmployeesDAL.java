package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import bd.OraConn;

public class EmployeesDAL {
	private SQLException ex;
	
	public SQLException getEx() { return ex; }
	
	public EmployeesDAL() {}
	


	public Vector<Employee> getEmployees() {
		Vector<Employee> employees = new Vector<Employee>();
		try ( Statement statement = OraConn.getConnection().createStatement(); ){
			String query = "SELECT * FROM HR.EMPLOYEES";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				employees.add(rs2Employee(resultSet));
			}
		} catch (SQLException ex) {System.out.println(ex); }
		return employees;
	}
	
	public Employee getEmployeeByEmployeeId(int id) {
		Employee emp = null;
		try ( Statement statement = OraConn.getConnection().createStatement(); ) {
			String query = "SELECT * FROM HR.EMPLOYEES "
					+ "WHERE EMPLOYEE_ID = " + id;
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.next();
			emp = rs2Employee(resultSet);
		} catch (SQLException ex) { System.out.println(ex); }
		return emp;
	}

	private Employee rs2Employee(ResultSet resultSet) {
		Employee emp = null;
		try {
			int col = 1;
			emp = new Employee(resultSet.getInt(col++));
			emp.setFirstName(resultSet.getNString(col++));
			emp.setLastName(resultSet.getNString(col++));
			emp.setEmail(resultSet.getNString(col++));
			emp.setPhoneNumber(resultSet.getNString(col++));
			emp.setHireDate(resultSet.getDate(col++).toLocalDate());
			emp.setJobId(resultSet.getNString(col++));
			emp.setSalary(resultSet.getInt(col++));
			col++;
			emp.setManagerId(resultSet.getInt(col++));
			emp.setDepartmentId(resultSet.getInt(col++));
		} catch (SQLException ex) { this.ex = ex; }
		return emp;
	}
	
	public int updateEmployee( Employee emp) {
		try (Statement statement = OraConn.getConnection().createStatement(); ){
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
			String hireDate = dtf.format(emp.getHireDate());
			
			String query = "UPDATE HR.EMPLOYEES SET "
					+ "FIRST_NAME = '" + emp.getFirstName() + "', "
					+ "LAST_NAME = '" + emp.getLastName() + "', "
					+ "EMAIL = '" + emp.getEmail() + "', "
					+ "PHONE_NUMBER = '" + emp.getPhoneNumber() + "', "
					+ "HIRE_DATE = to_date('" + hireDate + "', 'yyyyMMdd'), "
					+ "JOB_ID = '" + emp.getJobId() + "', "
					+ "SALARY = '" + emp.getSalary() + "', "
					+ "MANAGER_ID = '" + emp.getManagerId() + "', "
					+ "DEPARTMENT_ID = '" + emp.getDepartmentId() + "' "
					+ "WHERE "
					+ "EMPLOYEE_ID = " + emp.getEmployeeId();
			int affectedRows = statement.executeUpdate(query);
			OraConn.getConnection().commit();
			return affectedRows;
		} catch (SQLException ex) { this.ex = ex; return 0; }
	}
	
	public boolean insertEmployee( Employee emp ) {
		try (Statement statement = OraConn.getConnection().createStatement(); ){
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
			String hireDate = dtf.format(emp.getHireDate());
			
			String query = "INSERT INTO HR.EMPLOYEES VALUES"
					+ "("+ emp.getEmployeeId() + ", '" + emp.getFirstName() + "', '"
					+ emp.getLastName() + "', '"
					+ emp.getEmail() + "', '"
					+ emp.getPhoneNumber() + "', "
					+ "to_date('" + hireDate + "', 'yyyyMMdd'), '"
					+ emp.getJobId() + "', "
					+ emp.getSalary() + ", null, "
					+ emp.getManagerId() + ", "
					+ emp.getDepartmentId() + ")";
					
			boolean success = statement.execute(query);
			OraConn.getConnection().commit();
			return success;
		} catch (SQLException ex) { this.ex = ex; return false; }
	}
	
	public boolean delEmployee( Employee emp ) {
		try ( Statement statement = OraConn.getConnection().createStatement(); ){
			String query = "DELETE FROM HR.EMPLOYEES WHERE EMPLOYEE_ID = " + emp.getEmployeeId();
			boolean success = statement.execute(query);
			OraConn.getConnection().commit();
			return success;
			} catch(SQLException ex) { this.ex = ex; return false; }
	}
}
