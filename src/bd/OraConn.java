package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OraConn {
	
	static Connection connection = null;

	public OraConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Brak sterownika Oracle JDBC.");
		}
		System.out.println("Sterownik Oracle JDBC został zarejestrowany.");
		return;
	}
	
	public static void open(String login, String passwd) throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf", login, passwd);
		} catch (SQLException ex) {
			System.out.println("Błąd łączenia z bazą! \nNumer błędu: " + ex.getErrorCode() + "\nOpis błędu: " + ex.getMessage());
			throw ex;
		}
		System.out.println("Połączenie zostało otwarte.");
		return;
	}
	
	public static void close() throws SQLException {
		try {
			connection.close();
			System.out.println("Połączenie zostało zamknięte.");
		} catch (SQLException ex) {
			System.out.println("Błąd zamykania połączenia! \nNumer błędu: " + ex.getErrorCode() + "\nOpis błędu: " + ex.getMessage());
			throw ex;
		}
	}

}
