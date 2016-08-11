package org.fasttrack.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class updateNomineeJDBC {
	  // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";  
	   static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";

	   //  Database credentials
	   static final String USER = "system";
	   static final String PASS = "system";
	   
	   
	   public static boolean update(int id,String name, String relation,String dob){
		   boolean x = false;
		   try{
			   Class.forName("oracle.jdbc.OracleDriver");
			   Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
			   ///////////////////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!////////////////////////////
			   
			   PreparedStatement ps = con.prepareStatement("update nominee set name=?, relationship=?,dob=TO_DATE(?,'mm-dd-yyyy') where nomineeId = ?");
			 
			   ps.setString(1, name);
			   ps.setString(2, relation);
			   ps.setString(3, dob);
			   ps.setInt(4,id);
		
			   
			   ResultSet rs = ps.executeQuery();
			   x=rs.next();
		   }catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		   return x;
	   }
	   
}
