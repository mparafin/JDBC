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
		System.out.println("Sterownik Oracle JDBC zosta³ zarejestrowany.");
		return;
	}
	
	public static void open() {
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf", "mparafin", "mparafin");
		} catch (SQLException ex) {
			System.out.println("B³¹d ³¹czenia z baz¹! \nNumer b³êdu: " + ex.getErrorCode() + "\nOpis b³êdu: " + ex.getMessage());
			return;
		}
		System.out.println("Po³¹czenie zosta³o otwarte.");
		return;
	}
	
	public static void close() {
		try {
			connection.close();
			System.out.println("Po³¹czenie zosta³o zamkniête.");
		} catch (SQLException ex) {
			System.out.println("B³¹d zamykania po³¹czenia! \nNumer b³êdu: " + ex.getErrorCode() + "\nOpis b³êdu: " + ex.getMessage());
		}
	}

}
