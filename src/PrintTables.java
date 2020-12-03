import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class PrintTables
{
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;

	public static void main(String[] args) 
	{
		init_db();  // open the connection to the database
		try
		{
			ResultSet rs =  stmt.executeQuery("SELECT * FROM students");

			 while (rs.next()) { int empid = rs.getInt(1); String firstname =
			 rs.getString("firstName"); String lastname = rs.getString("lastName"); 
			 System.out.println(empid + " " + firstname + " " + lastname ); }
			 
		}
		catch (SQLException sqle)
		{
			System.out.println("Error: failed to get number of records");
		}
		try
		{
			con.close();
		}
		catch (SQLException sqle)
		{
			System.out.println("Error: failed to close the database");
		}
	}
	public static void init_db()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/assignment_data_baseSQL?useTimezone=true&serverTimezone=UTC";// shows which table is in use
			con = DriverManager.getConnection(url, "root", "password"); // make sure you have password as password(sometimes it is admin
			stmt = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Error: Failed to connect to database\n" + e.getMessage());
		}
		
	}

	
}


	