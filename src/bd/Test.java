package bd;


import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		try {
			OraConn.open("mparafin", "mparafin");
			OraConn.close();
		} catch (SQLException ex){}
	}

}
