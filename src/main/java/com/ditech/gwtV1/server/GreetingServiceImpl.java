package com.ditech.gwtV1.server;

import com.ditech.gwtV1.client.GreetingService;
import com.ditech.gwtV1.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		if (!FieldVerifier.isValidName(input)) {
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		return "Hello, Vlad123 Dascaleac ";
	}
}
