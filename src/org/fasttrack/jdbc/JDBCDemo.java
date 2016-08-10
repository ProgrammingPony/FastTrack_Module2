package org.fasttrack.jdbc;
import java.sql.*;

public class JDBCDemo {
	static Connection con = null;
	static PreparedStatement st = null;
	static ResultSet rs = null;
	
	public boolean check(String name) throws ClassNotFoundException, SQLException{
		String Driver = "oracle.jdbc.OracleDriver";
		String URL = "jdbc:oracle:thin:@10.101.1.111:1521:xe";
		String username = "system";
		String password = "system";
		String query = "select * from Policy";
		Class.forName(Driver);
		con = DriverManager.getConnection(URL, username, password);
		st = con.prepareStatement(query);

		System.out.println("here in jdbc 1");
		rs = st.executeQuery(query);	
		while(rs.next()){
        String column1 = rs.getString("policyName");
        column1.toLowerCase();
        name.toLowerCase();
        if(column1.equals(name)){
        	return false;
        }
		}
		
		st.close();
		rs.close();
		con.close();
		return true;
		
	}
	
	public boolean insertPolicy(int id, String name, int min, int max, int numNominee, String pre, int[] elements) throws ClassNotFoundException, SQLException{
		String Driver = "oracle.jdbc.OracleDriver";
		String URL = "jdbc:oracle:thin:@10.101.1.111:1521:xe";
		String username = "system";
		String password = "system";
		String query1 = "insert into policy values(?,?,?,?,?,'Y',?)";
		Class.forName(Driver);
		con = DriverManager.getConnection(URL, username, password);
		st = con.prepareStatement(query1);
		
		st.setInt(1, id);
		st.setString(2, name);
		st.setInt(3, min);
		st.setInt(4, max);
		st.setInt(5, numNominee);
		st.setString(6, pre);
		st.executeUpdate();	// ???
		String query3 = "select * from tenure";
		st = con.prepareStatement(query3);
		ResultSet rs = st.executeQuery();
		
		
		String query2 = null;
		int i = 0;
		while(rs.next()){
			if(elements[i] == 1){
				int k = i+1;
				query2 = "insert into policyHasTenure values(id,?)";
				st = con.prepareStatement(query1);
				st.setInt(1,rs.getInt("tenureLength"));
				st.executeUpdate();
				i++;
			
			}
		}
		
		
		st.close();
		//rs.close();
		con.close();
		return true;
		
	}
	
}
