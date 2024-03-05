package com.ditech.gwtV1.client;
 
import java.util.ArrayList;

import com.ditech.gwtV1.shared.models.EmployeeLevel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
 
public class Employee2 implements EntryPoint {
 
//	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private Button sendButton = new Button("Send");
 
	public void onModuleLoad() {
		RootPanel.get("nameFieldContainer").add(createLabeledTextBox("Name", "GWT User"));
		onSendBtnClick();
	}
 
	private Widget createLabeledTextBox(String labelText, String defaultText) {
		TextBox textBox = new TextBox();
		textBox.getElement().setPropertyString("placeholder", "Please enter " + labelText.toLowerCase());
		textBox.setText(defaultText);
		return textBox;
	}
 
	private void onSendBtnClick() {
		sendButton.addClickHandler(event -> sendNameToServer());
		sendButton.addStyleName("sendButton");
		RootPanel.get("sendButtonContainer").add(sendButton);
	}
 
	private void createAndDisplayTable(ArrayList<EmployeeLevel> result) {
		Button createBtn = new Button("Add");
		createBtn.setStyleName("newButtonStyle");
		RootPanel.get("tableContainer").add(createBtn);
 
		FlexTable flexTable = new FlexTable();
		flexTable.addStyleName("flexTable");
		flexTable.getRowFormatter().addStyleName(0, "flexTableHeader");
		RootPanel.get("tableContainer").add(flexTable);
 
		flexTable.setText(0, 0, "Edit");
		flexTable.setText(0, 1, "Remove");
		flexTable.setText(0, 2, "ID");
		flexTable.setText(0, 3, "VERSION");
		flexTable.setText(0, 4, "DESCRIPTION");
		
		int finalRow = 1;
		for (EmployeeLevel qualifying : result) {
			final int currentRow = finalRow;
			flexTable.setWidget(currentRow, 0,
					createButton("fas fa-pencil-alt", event -> openEditPage(currentRow, result, false)));
			flexTable.setWidget(currentRow, 1,
					createButton("fas fa-light fa-trash", event -> handleDeleteButtonClick(qualifying.getId())));
			flexTable.setText(currentRow, 2, qualifying.getId().toString());
			flexTable.setText(currentRow, 3, qualifying.getVersion().toString());
			flexTable.setText(currentRow, 4, qualifying.getDescription());
			finalRow++;
		}
	}
 
	private Button createButton(String iconClass, ClickHandler clickHandler) {
		Button button = new Button();
		HTML icon = new HTML("<i class=\"" + iconClass + "\"></i> ");
		button.getElement().appendChild(icon.getElement());
		button.addClickHandler(clickHandler);
		return button;
	}
 
	private void openEditPage(int rowIndex, ArrayList<EmployeeLevel> result, boolean isNewEmployee) {
		EmployeeLevel selectedEmployee = result.get(rowIndex - 1); // Adjusting index due to header row
		String id = String.valueOf(selectedEmployee.getId());
		String version = String.valueOf(selectedEmployee.getVersion());
		String description = selectedEmployee.getDescription();
 
		EditEmployeePage editPage = new EditEmployeePage(id, version, description, isNewEmployee,
				new AsyncCallback<ArrayList<EmployeeLevel>>() {
					@Override
					public void onSuccess(ArrayList<EmployeeLevel> result) {
						sendNameToServer();
					}
 
					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
					}
				});
		editPage.center();
		editPage.show();
	}
 
	private void sendNameToServer() {
//		greetingService.greetServer(new AsyncCallback<ArrayList<EmployeeLevel>>() {
//			@Override
//			public void onFailure(Throwable caught) {
//			}
// 
//			@Override
//			public void onSuccess(ArrayList<EmployeeLevel> result) {
//				createAndDisplayTable(result);
//				RootPanel.get("tableContainer").setVisible(true);
//			}
//		});
	}
 
	private void handleDeleteButtonClick(Long id) {
//		greetingService.deleteEmployeeLevel(id, new AsyncCallback<ArrayList<EmployeeLevel>>() {
//			@Override
//			public void onFailure(Throwable caught) {
//				caught.printStackTrace();
//			}
// 
//			@Override
//			public void onSuccess(ArrayList<EmployeeLevel> result) {
//				sendNameToServer();
//			}
//		});
	}
 
//	private void openCreatePage(int rowIndex, ArrayList<EmployeeLevel> result, boolean isNewEmployee) {
//		if (!isNewEmployee) {
//			EmployeeLevel selectedEmployee = result.get(rowIndex - 1); // Adjusting index due to header row
//			String id = String.valueOf(selectedEmployee.getId());
//			String version = String.valueOf(selectedEmployee.getVersion());
//			String description = selectedEmployee.getDescription();
//
//			EditEmployeePage editPage = new EditEmployeePage(description, isNewEmployee,
//					new AsyncCallback<ArrayList<EmployeeLevel>>() {
//						@Override
//						public void onSuccess(ArrayList<EmployeeLevel> result) {
//							sendNameToServer();
//						}
//
//						@Override
//						public void onFailure(Throwable caught) {
//							caught.printStackTrace();
//						}
//					});
//			editPage.center();
//			editPage.show();
//		} else {
//			displayInputAndSaveButton();
//		}
//	}
}
 












