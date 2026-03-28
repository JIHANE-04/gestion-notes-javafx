package uihi;
import java.sql.*;


public class DatabaseConnection  {

	public Connection cn=null;
	public static Connection connexionDB () {
	try {
	Class.forName ("com.mysql.cj.jdbc.Driver"); 
	Connection cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/databaseapp", "root", "");
	return cn;
	}
	catch (ClassNotFoundException | SQLException e) {
	System.out.println("connexion echouee");
	e.printStackTrace();
	return null;
	
        
}
	}
}
