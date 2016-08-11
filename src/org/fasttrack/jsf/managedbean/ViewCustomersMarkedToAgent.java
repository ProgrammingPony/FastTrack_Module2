package org.fasttrack.jsf.managedbean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="viewCustomersMarkedToAgent")
@RequestScoped
public class ViewCustomersMarkedToAgent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int agentId;
	private int policyId;
	private List<String> policyNames;
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getPolicyNames() {
		return policyNames;
	}

	public void setPolicyNames(List<String> policyNames) {
		this.policyNames = policyNames;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	
	public 

}