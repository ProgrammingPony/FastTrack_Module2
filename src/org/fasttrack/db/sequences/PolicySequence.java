package org.fasttrack.db.sequences;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.fasttrack.db.DBHelper;

public class PolicySequence {
	private static int nextId = -1;

	public int getNextId() throws SQLException {
		System.out.println("hx1");
		if (nextId < 0) {
			try {
				Class.forName(DBHelper.getJdbcDriver());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection con = DBHelper.getDatabaseConnection();
			Statement st;
			

				st = con.createStatement();
				System.out.println("hx2");
				System.out.println("here in sequence page");
				ResultSet rs = st.executeQuery("SELECT MAX(policyId) FROM policy");
				System.out.println("hx3");
				if (rs.next()){
			        int maxId = rs.getInt(1);
			        
			        if (maxId < 100)
			        	nextId = 100;
			        else
			        	nextId = maxId + 1;
				}
				
				st.close();
				rs.close();
				con.close();

		}
		
		return nextId++;
	}
	
}
