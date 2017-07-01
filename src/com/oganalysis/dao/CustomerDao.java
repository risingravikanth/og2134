package com.oganalysis.dao;

import java.util.Map;

public interface CustomerDao {
	public boolean saveCustomer(Map<String,String> customerMap);
}
