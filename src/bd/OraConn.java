package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OraConn {
	
	static Connection connection = null;

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Brak sterownika Oracle JDBC.");
		}
		System.out.println("Sterownik Oracle JDBC zosta� zarejestrowany.");
		return;
	}
	
	public static void open() {
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf", "mparafin", "mparafin");
		} catch (SQLException ex) {
			System.out.println("B��d ��czenia z baz�! \nNumer b��du: " + ex.getErrorCode() + "\nOpis b��du: " + ex.getMessage());
			return;
		}
		System.out.println("Po��czenie zosta�o otwarte.");
		return;
	}
	
	public static void close() {
		try {
			connection.close();
			System.out.println("Po��czenie zosta�o zamkni�te.");
		} catch (SQLException ex) {
			System.out.println("B��d zamykania po��czenia! \nNumer b��du: " + ex.getErrorCode() + "\nOpis b��du: " + ex.getMessage());
		}
	}

}
