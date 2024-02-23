package com.ditech.gwtproject.client.services.employeeLevel;

import java.util.ArrayList;

import com.ditech.gwtproject.shared.EmployeeLevel;
//import com.ditech.gwtproject.client.services.CommonServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;

// extends CommonServiceAsync 
public interface EmployeeLevelServiceAsync {

	void findAll(String input, AsyncCallback<ArrayList<EmployeeLevel>> callback) throws IllegalArgumentException;
}
