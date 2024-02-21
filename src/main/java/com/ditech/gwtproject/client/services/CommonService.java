package com.ditech.gwtproject.client.services;

import com.google.gwt.http.client.Request;
public interface CommonService {

	Request findAll(String url) throws IllegalArgumentException;
}
