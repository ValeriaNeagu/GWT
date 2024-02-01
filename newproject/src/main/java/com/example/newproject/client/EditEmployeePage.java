package com.example.newproject.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EditEmployeePage extends PopupPanel {

	private VerticalPanel editVerticalPanel = new VerticalPanel();
	private TextBox firstNameTextBox = new TextBox();
	private TextBox lastNameTextBox = new TextBox();
	private TextBox hireDateTextBox = new TextBox();
	private TextBox birthDateTextBox = new TextBox();
	private Button saveButton = new Button("Save");
	private Button backButton = new Button("Back");
	private Newproject newproject;

	public EditEmployeePage(String firstName, String lastName, String hireDate, String birthDate,Newproject newproject) {
		super(true);
		editVerticalPanel.setStyleName("noBorder");
		HorizontalPanel buttonPanel = new HorizontalPanel();
		this.newproject = newproject;

		Label editTitleLabel = new Label("Edit Employee Information");

		FlexTable editFlexTable = new FlexTable();
		editFlexTable.setStyleName("gwt-Table");

		editFlexTable.setText(0, 0, "First name");
		editFlexTable.setText(0, 1, "Last name");
		editFlexTable.setText(0, 2, "Hire date");
		editFlexTable.setText(0, 3, "Birth date");

		firstNameTextBox.setValue(firstName);
		lastNameTextBox.setValue(lastName);
		hireDateTextBox.setValue(hireDate);
		birthDateTextBox.setValue(birthDate);

		editFlexTable.setWidget(1, 0, firstNameTextBox);
		editFlexTable.setWidget(1, 1, lastNameTextBox);
		editFlexTable.setWidget(1, 2, hireDateTextBox);
		editFlexTable.setWidget(1, 3, birthDateTextBox);

		buttonPanel.add(saveButton);
		buttonPanel.add(backButton);
		editFlexTable.setWidget(2, 0, buttonPanel);

		saveButton.addClickHandler(event -> saveChanges());
		backButton.addClickHandler(event -> goBack());

		editFlexTable.getRowFormatter().addStyleName(0, "headerRow");
		editFlexTable.setWidth("100%");
		editTitleLabel.getElement().getStyle().setMarginBottom(10, com.google.gwt.dom.client.Style.Unit.PX);

		editVerticalPanel.add(editTitleLabel);
		editVerticalPanel.add(editFlexTable);
		editVerticalPanel.add(buttonPanel);

		setWidget(editVerticalPanel);
	}

	private void saveChanges() {
		hide(); 
	}
	
	private void goBack() {
	    hide();
	    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	        @Override
	        public void execute() {
	            newproject.showOriginalContent();
	        }
	    });
	}

}
