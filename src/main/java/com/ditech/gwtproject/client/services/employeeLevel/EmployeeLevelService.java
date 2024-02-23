package com.ditech.gwtproject.client.services.employeeLevel;

import java.util.ArrayList;

import com.ditech.gwtproject.shared.EmployeeLevel;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("employeeLevel")
public interface EmployeeLevelService extends RemoteService {
	ArrayList<EmployeeLevel> findAll(String name) throws IllegalArgumentException;
}
