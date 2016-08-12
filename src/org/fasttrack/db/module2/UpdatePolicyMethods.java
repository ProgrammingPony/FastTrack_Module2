package org.fasttrack.db.module2;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fasttrack.db.DBHelperMuhsanah;
import org.fasttrack.jdbc.JDBCUpdatePolicy;



public class UpdatePolicyMethods {
	//static JDBCUpdatePolicy jdbc = new JDBCUpdatePolicy();

	// display policy info
	public static void displayPolicy(HttpServletRequest request, HttpServletResponse response){
		String policyId = request.getParameter("policyId");
		System.out.println( request.getParameter("policyId"));
		
		try {
			
			int p = Integer.valueOf(request.getParameter("policyId").trim()); // to int
			
			if(policyId != null) { 

				//Check if policy Exists
				boolean policyExist = JDBCUpdatePolicy.checkPolicy(p);
			    
			    // Process result set
				if(policyExist){ // policyId exists 
					// update policy
					
					HttpSession hsession = request.getSession();
					
					hsession.setAttribute("policy_id2", p);
					// get policy name
					
					hsession.setAttribute("policy_name2", JDBCUpdatePolicy.getPolicyName(p));
					hsession.setAttribute("policy_nom2", JDBCUpdatePolicy.getPolicyNom(p));
					hsession.setAttribute("policy_sumMin2", JDBCUpdatePolicy.getPolicySumMin(p));
					hsession.setAttribute("policy_sumMax2", JDBCUpdatePolicy.getPolicySumMax(p));
					hsession.setAttribute("policy_prereq2", JDBCUpdatePolicy.getPolicyPrereq(p));
					hsession.setAttribute("policy_isActive2", JDBCUpdatePolicy.getPolicyActive(p));
					hsession.setAttribute("policy_ID2", policyId);
					

				    // get tenure length ArrayList

				    ArrayList<Integer> list = JDBCUpdatePolicy.getTenureArray(p);

				    // set tenure attributes
				    for(int i : list){
				    	if(i == 1){
				    		hsession.setAttribute("tenure_1yr2", "");
				    	} else if(i == 2){
				    		hsession.setAttribute("tenure_2yr2", "");
				    	} else if(i == 3){
				    		hsession.setAttribute("tenure_3yr2", "");
				    	} else if(i == 4){
				    		hsession.setAttribute("tenure_4yr2", "");
				    	} else if(i == 5){
				    		hsession.setAttribute("tenure_5yr2", "");
				    	} else if(i == 6){
				    		hsession.setAttribute("tenure_6yr2", "");
				    	}
				    }
 
				}
		
				else{ // policy ID does not exist
					HttpSession hsession = request.getSession();
					hsession.setAttribute("error_msg2", "Invalid Policy ID");
					hsession.setAttribute("policy_ID2", policyId);
			    }
			    
		    
			} else { // an empty field
				HttpSession hsession = request.getSession();
				hsession.setAttribute("error_msg2", "Type in all the fields");
				hsession.setAttribute("policy_ID2", policyId);
		    
			}
			
		} catch(NumberFormatException e){ // not an int
			HttpSession hsession = request.getSession();
			hsession.setAttribute("error_msg2", "Invalid Policy ID - not int");
			hsession.setAttribute("policy_ID2", policyId);
	  
		}

	

	}

	// update policy
	public static void updatePolicy (HttpServletRequest request, HttpServletResponse response) {
		
		String policyId = request.getParameter("policy_id2");
		String policyNom = request.getParameter("policy_nom2").trim();
		String policySumMax = request.getParameter("policy_sumMax2").trim();
		String policySumMin = request.getParameter("policy_sumMin2").trim();
		String policy_name2 = request.getParameter("policy_name2").trim();
		String policy_prereq2 = request.getParameter("policy_prereq2").trim();
		String policy_isActive2 = request.getParameter("policy_isActive2").trim();
		//System.out.println("policy sum max: "+policySumMax);
		int tenure_1yr2= 0;
		int tenure_2yr2 = 0;
		int tenure_3yr2 = 0;
		int tenure_4yr2 = 0;
		int tenure_5yr2 = 0;
		int tenure_6yr2 = 0;
			
		//int[] elements = new int[6];
		ArrayList<Integer> elements = new ArrayList<Integer>();
		String tenure;
		for(int k = 1; k<7; k++){
			tenure = request.getParameter("tenure"+k);
			System.out.println("in tenure"+tenure);
			
			if(tenure != null){
				elements.add(k);
				System.out.println("in tenure "+k);
				if(k==1){ // tenure 1
					tenure_1yr2 =1;
				} else if(k==2){ // tenure 2
					tenure_2yr2 =2;
				} else if(k==3){
					tenure_3yr2 =3;
				} else if(k==4){
					tenure_4yr2 =4;
				} else if(k==5){
					tenure_5yr2 =5;
				} else if(k==6){
					tenure_6yr2 =6;
				}
			
			}
		}
		
		System.out.println("elements length is "+elements.size());
		// all inputs filled
		if (policyId.length() != 0 && policy_name2.length() != 0
					&& policy_prereq2.length() != 0 && policyNom.length() != 0
					&& policySumMax.length() != 0 && policySumMin.length() != 0 && elements.size() >0 && elements != null) {
				
			try { //policy id, nominee, and isActive can't be changed
					
				int p = Integer.valueOf(request.getParameter("policy_id2").trim()); // to
				int policy_nom2 = Integer.valueOf(request.getParameter("policy_nom2").trim());
				int policy_sumMax2 = Integer.valueOf(request.getParameter("policy_sumMax2").trim());
				int policy_sumMin2 = Integer.valueOf(request.getParameter("policy_sumMin2").trim());
				
				if(policy_sumMin2 <= policy_sumMax2 && policy_sumMin2>0 && policy_sumMin2>0){

					if(policy_nom2>0){ //nom positive
						
						System.out.println("nom is "+policy_nom2);
						boolean policyNameExist = JDBCUpdatePolicy.checkPolicyName(policy_name2, p);
						
						if(!policyNameExist){ // if policy name is unique
	
							// Update Policy
							boolean successPolicy = JDBCUpdatePolicy.updatePolicy(policy_name2, policy_sumMin2, policy_sumMax2, policy_prereq2, p, policy_nom2);
							// Remove old tenure from policyHasTenure
							boolean deleteTenure = JDBCUpdatePolicy.deleteTenure(p);
							boolean successTenure1;
							boolean successTenure2;
							boolean successTenure3;
							boolean successTenure4;
							boolean successTenure5;
							boolean successTenure6;
							
							 // Add tenure in policyHasTenure
							 
							 // tenure 1
							 if(tenure_1yr2 != 0){
							 	successTenure1 = JDBCUpdatePolicy.addTenure(p, 1);
							 } 
							 // tenure 2
							 if(tenure_2yr2 != 0){
								 successTenure2 = JDBCUpdatePolicy.addTenure(p, 2);
							 } if(tenure_3yr2 != 0){
								 successTenure3 = JDBCUpdatePolicy.addTenure(p, 3);
							 } if(tenure_4yr2 != 0){
								 successTenure4 = JDBCUpdatePolicy.addTenure(p, 4);
							 } if(tenure_5yr2 != 0){
								 successTenure5 = JDBCUpdatePolicy.addTenure(p, 5);
							 } if(tenure_6yr2 != 0){
								 successTenure6 = JDBCUpdatePolicy.addTenure(p, 6);
							 }
							
							if(successPolicy && deleteTenure){
		
								HttpSession hsession = request.getSession();
								hsession.setAttribute("error_msg2",
										"Successfully updated the policy");
				
							} else {
								HttpSession hsession = request.getSession();
								hsession.setAttribute("error_msg2",
										"Failed to update policy");
							
							}
	
						} else { // name is not unique
							HttpSession hsession = request.getSession();
							hsession.setAttribute("error_msg2", "Policy name should be unique");
							
							hsession.setAttribute("policy_id2", policyId);
							hsession.setAttribute("policy_name2", policy_name2);
							hsession.setAttribute("policy_nom2", policyNom);
							hsession.setAttribute("policy_sumMin2", policySumMin);
							hsession.setAttribute("policy_sumMax2", policySumMax);
							hsession.setAttribute("policy_prereq2", policy_prereq2);
							hsession.setAttribute("policy_isActive2", policy_isActive2);
							
							if(tenure_1yr2 != 0){
					    		hsession.setAttribute("tenure_1yr2", "");
					    	} if(tenure_2yr2 != 0){
					    		hsession.setAttribute("tenure_2yr2", "");
					    	} if(tenure_3yr2 != 0){
					    		hsession.setAttribute("tenure_3yr2", "");
					    	} if(tenure_4yr2 != 0){
					    		hsession.setAttribute("tenure_4yr2", "");
					    	} if(tenure_5yr2 != 0){
					    		hsession.setAttribute("tenure_5yr2", "");
					    	} if(tenure_6yr2 != 0){
					    		hsession.setAttribute("tenure_6yr2", "");
					    	}
							
							
						}
					} else{
						HttpSession hsession = request.getSession();
						hsession.setAttribute("error_msg2", "Nominee should be positive values");
						
						hsession.setAttribute("policy_id2", policyId);
						hsession.setAttribute("policy_name2", policy_name2);
						hsession.setAttribute("policy_nom2", policyNom);
						hsession.setAttribute("policy_sumMin2", policySumMin);
						hsession.setAttribute("policy_sumMax2", policySumMax);
						hsession.setAttribute("policy_prereq2", policy_prereq2);
						hsession.setAttribute("policy_isActive2", policy_isActive2);
						
						if(tenure_1yr2 != 0){
				    		hsession.setAttribute("tenure_1yr2", "");
				    	} if(tenure_2yr2 != 0){
				    		hsession.setAttribute("tenure_2yr2", "");
				    	} if(tenure_3yr2 != 0){
				    		hsession.setAttribute("tenure_3yr2", "");
				    	} if(tenure_4yr2 != 0){
				    		hsession.setAttribute("tenure_4yr2", "");
				    	} if(tenure_5yr2 != 0){
				    		hsession.setAttribute("tenure_5yr2", "");
				    	} if(tenure_6yr2 != 0){
				    		hsession.setAttribute("tenure_6yr2", "");
				    	}
						
						
					}
				
				}
				else{ // sum Min greater than max or not positive
					HttpSession hsession = request.getSession();
					hsession.setAttribute("error_msg2", "Sum assured minimum should be less than sum assured maximum, and both should be positive values");
					
					hsession.setAttribute("policy_id2", policyId);
					hsession.setAttribute("policy_name2", policy_name2);
					hsession.setAttribute("policy_nom2", policyNom);
					hsession.setAttribute("policy_sumMin2", policySumMin);
					hsession.setAttribute("policy_sumMax2", policySumMax);
					hsession.setAttribute("policy_prereq2", policy_prereq2);
					hsession.setAttribute("policy_isActive2", policy_isActive2);
					
					if(tenure_1yr2 != 0){
			    		hsession.setAttribute("tenure_1yr2", "");
			    	} if(tenure_2yr2 != 0){
			    		hsession.setAttribute("tenure_2yr2", "");
			    	} if(tenure_3yr2 != 0){
			    		hsession.setAttribute("tenure_3yr2", "");
			    	} if(tenure_4yr2 != 0){
			    		hsession.setAttribute("tenure_4yr2", "");
			    	} if(tenure_5yr2 != 0){
			    		hsession.setAttribute("tenure_5yr2", "");
			    	} if(tenure_6yr2 != 0){
			    		hsession.setAttribute("tenure_6yr2", "");
			    	}
					
					
				}
			} catch (NumberFormatException e) { // not an int
				HttpSession hsession = request.getSession();
				hsession.setAttribute("error_msg2", "Invalid Inputs");
				
				hsession.setAttribute("policy_id2", policyId);
				hsession.setAttribute("policy_name2", policy_name2);
				hsession.setAttribute("policy_nom2", policyNom);
				hsession.setAttribute("policy_sumMin2", policySumMin);
				hsession.setAttribute("policy_sumMax2", policySumMax);
				hsession.setAttribute("policy_prereq2", policy_prereq2);
				hsession.setAttribute("policy_isActive2", policy_isActive2);
				
				if(tenure_1yr2 != 0){
		    		hsession.setAttribute("tenure_1yr2", "");
		    	} if(tenure_2yr2 != 0){
		    		hsession.setAttribute("tenure_2yr2", "");
		    	} if(tenure_3yr2 != 0){
		    		hsession.setAttribute("tenure_3yr2", "");
		    	} if(tenure_4yr2 != 0){
		    		hsession.setAttribute("tenure_4yr2", "");
		    	} if(tenure_5yr2 != 0){
		    		hsession.setAttribute("tenure_5yr2", "");
		    	} if(tenure_6yr2 != 0){
		    		hsession.setAttribute("tenure_6yr2", "");
		    	}
				
				
			}

		} else {
			HttpSession hsession = request.getSession();
			hsession.setAttribute("error_msg2", "Type in all the fields");
			
			hsession.setAttribute("policy_id2", policyId);
			hsession.setAttribute("policy_name2", policy_name2);
			hsession.setAttribute("policy_nom2", policyNom);
			hsession.setAttribute("policy_sumMin2", policySumMin);
			hsession.setAttribute("policy_sumMax2", policySumMax);
			hsession.setAttribute("policy_prereq2", policy_prereq2);
			hsession.setAttribute("policy_isActive2", policy_isActive2);
			
			if(tenure_1yr2 != 0){
	    		hsession.setAttribute("tenure_1yr2", "");
	    	} if(tenure_2yr2 != 0){
	    		hsession.setAttribute("tenure_2yr2", "");
	    	} if(tenure_3yr2 != 0){
	    		hsession.setAttribute("tenure_3yr2", "");
	    	} if(tenure_4yr2 != 0){
	    		hsession.setAttribute("tenure_4yr2", "");
	    	} if(tenure_5yr2 != 0){
	    		hsession.setAttribute("tenure_5yr2", "");
	    	} if(tenure_6yr2 != 0){
	    		hsession.setAttribute("tenure_6yr2", "");
	    	}
			
		
		}


	}






}