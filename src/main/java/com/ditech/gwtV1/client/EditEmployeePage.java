package com.ditech.gwtV1.client;

import java.util.ArrayList;

import com.ditech.gwtV1.shared.models.EmployeeLevel;
import com.ditech.gwtV1.shared.models.Qualifying;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EditEmployeePage extends DialogBox {

//	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private boolean isNewEmployee;
	private TextBox idTextBox = new TextBox();
	private TextBox versionTextBox = new TextBox();
	private TextBox descriptionTextBox = new TextBox();
	private Button saveButton = new Button("Save");
	private Button closeDialogBtn = new Button("Close");

	public void showDialogBox(Qualifying qualifying) {
		createUI();
		descriptionTextBox.setText(qualifying.getDescription());
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				handleSaveButtonClick();
			}
		});
		closeDialogBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				closeEditPage();
			}
		});
	}

	public EditEmployeePage(Qualifying qualifying) {
		this.isNewEmployee = qualifying.getId() == null;
		createUI();
		if (!isNewEmployee) {
//	            idTextBox.setText(id);
//	            versionTextBox.setText(version);
			descriptionTextBox.setText(qualifying.getDescription());
			saveButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					handleSaveButtonClick();
				}
			});
			closeDialogBtn.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					closeEditPage();
				}
			});
		}

	}

	public EditEmployeePage(String id, String version, String description, boolean isNewEmployee,
			AsyncCallback<ArrayList<EmployeeLevel>> callback) {
		this.isNewEmployee = isNewEmployee;
		createUI();
		if (!isNewEmployee) {
			idTextBox.setText(id);
			versionTextBox.setText(version);
			descriptionTextBox.setText(description);
			saveButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					handleSaveButtonClick();
				}
			});
			closeDialogBtn.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					closeEditPage();
				}
			});
		}

	}

	private void createUI() {
		VerticalPanel contentPanel = new VerticalPanel();
//		contentPanel.add(new Label("IdEditpage:"));
//		contentPanel.add(idTextBox);
//		contentPanel.add(new Label("Version:"));
//		contentPanel.add(versionTextBox);
		contentPanel.add(new Label("Description:"));
		contentPanel.add(descriptionTextBox);

		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.add(saveButton);
		buttonPanel.add(closeDialogBtn);

		contentPanel.add(buttonPanel);

		if (isNewEmployee) {
			hideInitialFields();
		}
		this.add(contentPanel);
	}

	private void hideInitialFields() {
		idTextBox.setVisible(false);
		versionTextBox.setVisible(false);
		descriptionTextBox.setVisible(false);
		saveButton.setVisible(false);
	}

	private void handleSaveButtonClick() {
//		String id = idTextBox.getText();
//		String version = versionTextBox.getText();
		String description = descriptionTextBox.getText();

		EmployeeLevel employee = new EmployeeLevel();
//		employee.setId(Long.parseLong(id));
//		employee.setVersion(Integer.parseInt(version));
		employee.setDescription(description);

		/*
		 * greetingService.saveUpdateEmployee(employee, new
		 * AsyncCallback<ArrayList<EmployeeLevel>>() {
		 * 
		 * @Override public void onFailure(Throwable caught) { caught.printStackTrace();
		 * }
		 * 
		 * @Override public void onSuccess(ArrayList<EmployeeLevel> result) {
		 * closeEditPage(); // notifyObservers(); if (callback != null) {
		 * callback.onSuccess(result); } } });
		 * 
		 */
	}

	private void closeEditPage() {
		this.hide();
	}
}
