package com.ditech.gwtproject.client.services;

import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CommonServiceAsync {
	void findAll(String url, AsyncCallback<List<Object>> callback);
//	default List<Object> findAll() {
//		doHTTPGet("http://localhost:8099/employeeLevel/findAll");
//		return null;
//	}
}
