package bd;


import java.sql.SQLException;
import java.util.Vector;

import dal.Employee;
import dal.EmployeesDAL;

public class Test {

	public static void main(String[] args) {
		try {
			OraConn.open("mparafin", "mparafin");
			EmployeesDAL dal = new EmployeesDAL();
			Vector<Employee> emps = dal.getEmployees();
			System.out.println(emps.size());
			
			Employee emp = dal.getEmployeeByEmployeeId(203);
			System.out.println(emp.getLastName());
			
			emp.setLastName("Smith");
			dal.updateEmployee(emp);
			emp = dal.getEmployeeByEmployeeId(203);
			System.out.println(dal.getEx());
			
			dal.insertEmployee(emp);
			System.out.println(dal.getEx());
			
			dal.delEmployee(emp);
			System.out.println(dal.getEx());
			
			OraConn.close();
		} catch (SQLException ex){}
	}

}
