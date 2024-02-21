package com.ditech.gwtproject.client;

import java.util.ArrayList;
import java.util.List;

import com.ditech.gwtproject.client.services.employeeLevel.EmployeeLevelService;
import com.ditech.gwtproject.client.services.employeeLevel.EmployeeLevelServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestGWT implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
//	private static final String SERVER_ERROR = "An error occurred while "
//			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
//	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	private final EmployeeLevelServiceAsync employeeLevelService = GWT.create(EmployeeLevelService.class);

	/**
	 * This is the entry point method.
	 */

	public static final int STATUS_CODE_OK = 200;

	private List<Object> allEmployees = new ArrayList<>();

//	public void doGet(String url) {
//		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
//		try {
//			Request response = builder.sendRequest(null, new RequestCallback() {
//				public void onError(Request request, Throwable exception) {
//				}
//
//				public void onResponseReceived(Request request, Response response) {
//				}
//			});
//		} catch (RequestException e) {
//		}
//	}

	private void setAllEmployee(List<Object> allEmployees) {
		this.allEmployees = allEmployees;
	}

	private List<Object> getAllEmployee() {
		return this.allEmployees;
	}

	public void onModuleLoad() {
//		GWT.debugger();
//		doGet("cevaaaa");
		GWT.log(" ---------- TestGWT public void onModuleLoad ");

//		EmployeeLevelService employeeLevelService = new EmployeeLevelService();
//		List list = employeeLevelService.getAllEmployeeLevel();

//		GWT.log(list.toString());
		final Button sendButton = new Button("Send D.V");
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();
		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			private String urlDummy;

			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {

//				greetingService.greetServer("not null", new AsyncCallback<String>() {
//					@Override
//					public void onFailure(Throwable caught) {
//					}
//					@Override
//					public void onSuccess(String result) {
//					}
//				});

//				greetingService.greetServer("cevaaaa", new AsyncCallback<String>() {
//					public void onFailure(Throwable caught) {
//					}
//
//					public void onSuccess(String result) {
//					}
//				});

				urlDummy = "http://localhost:8099/employeeLevel/findAll";
				employeeLevelService.findAll(urlDummy, new AsyncCallback<List<Object>>() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(List<Object> result) {
//						allEmployees = result;
//						setAllEmployee(result);
					}
				});

				GWT.log("------------>>> getAllEmployee " + getAllEmployee().toString());
				GWT.debugger();

				// First, we validate the input.
//				errorLabel.setText("");
//				String textToServer = nameField.getText();
//				if (!FieldVerifier.isValidName(textToServer)) {
//					errorLabel.setText("Please enter at least four characters");
//					return;
//				}

				// Then, we send the input to the server.
//				sendButton.setEnabled(false);
//				textToServerLabel.setText(textToServer);
//				serverResponseLabel.setText("");

				// start test 1

//				String url = "https://services.syncfusion.com/js/production/api/Orders";
//				String requestData = "/employees";
//				RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
	}
}
