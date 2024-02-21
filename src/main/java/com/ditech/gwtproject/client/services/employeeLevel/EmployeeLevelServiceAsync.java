package com.ditech.gwtproject.client.services.employeeLevel;

import java.util.List;

//import com.ditech.gwtproject.client.services.CommonServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;

// extends CommonServiceAsync 
public interface EmployeeLevelServiceAsync {

	void findAll(String input, AsyncCallback<List<Object>> callback) throws IllegalArgumentException;
}
