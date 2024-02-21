package com.ditech.gwtproject.client.services.employeeLevel;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("employeeLevel")
public interface EmployeeLevelService extends RemoteService {
	List<Object> findAll(String name) throws IllegalArgumentException;
}
