package com.ditech.gwtV1.client.services.employeeLevel;

import java.util.ArrayList;

import com.ditech.gwtV1.shared.models.EmployeeLevel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EmployeeLevelServiceAsync {

	void findAll(AsyncCallback<ArrayList<EmployeeLevel>> callback) throws IllegalArgumentException;
}
