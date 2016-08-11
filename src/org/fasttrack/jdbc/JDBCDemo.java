package org.fasttrack.jdbc;


import java.sql.*;

import org.fasttrack.db.*;

public class JDBCDemo {
	static Connection con = null;
	static PreparedStatement st = null;
	static ResultSet rs = null;
	
	public boolean check(String name) throws ClassNotFoundException, SQLException{
		String query = "select * from Policy";
		Class.forName(DBHelper.getJdbcDriver());
		con = DBHelper.getDatabaseConnection();
		st = con.prepareStatement(query);


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
		String query1 = "insert into policy values(?,?,?,?,?,?,?)";
		
		Class.forName(DBHelper.getJdbcDriver());
		con = DBHelper.getDatabaseConnection();
		st = con.prepareStatement(query1);

		st.setInt(1, id);
		st.setString(2, name);
		st.setInt(3, min);
		st.setInt(4, max);
		st.setInt(5, numNominee);
		st.setString(6, "Y");
		st.setString(7, pre);
		st.executeUpdate();	// ???

		
		String query2 = null;
		for(int i = 0; i< 6; i++){

			if(elements[i] == 1){
				int k = i+1;
				query2 = "insert into policyHasTenure values(?,?)";
				st = con.prepareStatement(query2);
				st.setInt(1,id);
				st.setInt(2, k);
				st.executeUpdate();
			}
		}

		
		st.close();
		//rs.close();
		con.close();
		return true;
		
	}
	
}
