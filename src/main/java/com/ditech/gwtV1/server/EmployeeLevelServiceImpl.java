package com.ditech.gwtV1.server;

import java.util.ArrayList;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.ditech.gwtV1.client.services.employeeLevel.EmployeeLevelService;
import com.ditech.gwtV1.shared.Util;
import com.ditech.gwtV1.shared.models.EmployeeLevel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class EmployeeLevelServiceImpl extends RemoteServiceServlet implements EmployeeLevelService {

	private final String EMPLOYEE_LEVEL_MAIN_PATH = Util.HOST + ":" + Util.PORT + "/" + Util.EMPLOYEE_LEVEL_PATH;

	@Override
	public ArrayList<EmployeeLevel> findAll() throws IllegalArgumentException {
		final String url = EMPLOYEE_LEVEL_MAIN_PATH + "/" + Util.FIND_ALL_PATH;
		ArrayList<EmployeeLevel> listTemp = null;
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet request = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(request);

//			CloseableHttpResponse response = HttpRequestMethods.doHttpGet(url);
			listTemp = new ObjectMapper().readValue(EntityUtils.toString(response.getEntity()),
					new TypeReference<ArrayList<EmployeeLevel>>() {
					});

//			listTemp = (List<Object>) HTTPRequestMethods.doHTTPGet(url);

			System.out.println(" EmployeeLevelServiceImpl public ArrayList<EmployeeLevel> findAll ");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTemp;
	}

}
