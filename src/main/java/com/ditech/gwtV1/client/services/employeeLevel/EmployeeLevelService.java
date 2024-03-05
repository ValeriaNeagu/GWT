package com.ditech.gwtV1.client.services.employeeLevel;

import java.util.ArrayList;

import com.ditech.gwtV1.shared.models.EmployeeLevel;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("employeeLevel")
public interface EmployeeLevelService extends RemoteService {
	ArrayList<EmployeeLevel> findAll() throws IllegalArgumentException;
}
