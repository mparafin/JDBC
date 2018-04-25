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
			
			OraConn.close();
		} catch (SQLException ex){}
	}

}
