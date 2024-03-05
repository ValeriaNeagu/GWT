package com.ditech.gwtV1.client;

import java.util.ArrayList;

import com.ditech.gwtV1.client.services.employeeLevel.EmployeeLevelService;
import com.ditech.gwtV1.client.services.employeeLevel.EmployeeLevelServiceAsync;
import com.ditech.gwtV1.client.services.qualifying.QualifyingService;
import com.ditech.gwtV1.client.services.qualifying.QualifyingServiceAsync;
import com.ditech.gwtV1.shared.models.EmployeeLevel;
import com.ditech.gwtV1.shared.models.Qualifying;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtV1 implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	private final EmployeeLevelServiceAsync employeeLevelService = GWT.create(EmployeeLevelService.class);

	private final QualifyingServiceAsync qualifyingService = GWT.create(QualifyingService.class);

	private final String tableContainerId = "tableContainerId";

	/**
	 * This is the entry point method.
	 */

	private void createGrid_DV() {
		CellTable<EmployeeLevel> cellTableOfQualifying = new CellTable<EmployeeLevel>();
		SimplePager pager = new SimplePager();
		pager.setDisplay(cellTableOfQualifying);
		pager.setPageSize(15);
		// The policy that determines how keyboard selection will work. Keyboard
		// selection is enabled.
		cellTableOfQualifying.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		cellTableOfQualifying.setTitle("Qualifying Table");

		// create table column
		TextColumn<EmployeeLevel> columnId = new TextColumn<EmployeeLevel>() {
			@Override
			public String getValue(EmployeeLevel object) {
				return object.getId().toString();
			}
		};
		cellTableOfQualifying.addColumn(columnId, "Id");
		TextColumn<EmployeeLevel> columnVersion = new TextColumn<EmployeeLevel>() {
			@Override
			public String getValue(EmployeeLevel object) {
				return object.getVersion().toString();
			}
		};
		cellTableOfQualifying.addColumn(columnVersion, "Version");
		TextColumn<EmployeeLevel> columnDescription = new TextColumn<EmployeeLevel>() {
			@Override
			public String getValue(EmployeeLevel object) {
				return object.getDescription();
			}
		};
		cellTableOfQualifying.addColumn(columnDescription, "Description");

		// Add edit and delete buttons
		ButtonCell buttonEdit = new ButtonCell();
		Column<EmployeeLevel, String> buttonColumnEdit = new Column<EmployeeLevel, String>(buttonEdit) {
			@Override
			public String getValue(EmployeeLevel object) {
				return "Edit";
			}
		};
		cellTableOfQualifying.addColumn(buttonColumnEdit, "Edit");
		buttonColumnEdit.setFieldUpdater(new FieldUpdater<EmployeeLevel, String>() {
			public void update(int index, EmployeeLevel object, String value) {
				Window.alert("You want to edit object with id: " + object.getId());
			}
		});

		// Create delete function
		ButtonCell buttonDelete = new ButtonCell();
		Column<EmployeeLevel, String> buttonColumnDelete = new Column<EmployeeLevel, String>(buttonDelete) {
			@Override
			public String getValue(EmployeeLevel object) {
				return "Delete";
			}
		};
		cellTableOfQualifying.addColumn(buttonColumnDelete, "Delete");
		// You can then set a FieldUpdater on the Column to be notified of clicks.
		buttonColumnDelete.setFieldUpdater(new FieldUpdater<EmployeeLevel, String>() {
			public void update(int index, EmployeeLevel object, String value) {
				// Value is the button value. Object is the row object.
				Window.alert("You want to delete object with id: " + object.getId());
//				qualifyingService.delete(object.getId(), new AsyncCallback<Void>() {
//					@Override
//					public void onSuccess(Void result) {
//						Window.alert("Object was deleted.");
//						Window.Location.reload();
//					}
//
//					@Override
//					public void onFailure(Throwable caught) {
//					}
//				});
			}
		});

		// add selection on rows
		final SingleSelectionModel<EmployeeLevel> selectionModel = new SingleSelectionModel<EmployeeLevel>();
		cellTableOfQualifying.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
			}
		});

		// populate table

		employeeLevelService.findAll(new AsyncCallback<ArrayList<EmployeeLevel>>() {
			@Override
			public void onSuccess(ArrayList<EmployeeLevel> result) {
				GWT.log("public void onSuccess " + result.get(0).toString());
				cellTableOfQualifying.setRowCount(result.size(), true);
				cellTableOfQualifying.setRowData(0, result);
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("public void onFailure " + caught.getMessage());
			}
		});

		Button saveButton = new Button("Save");
		saveButton.setStyleName("sendButton");

		TextBox qualifyingDescriptionTextBox = new TextBox();
		// qualifyingDescriptionTextBox.setText("Qualifying description.");
		qualifyingDescriptionTextBox.setFocus(true);

		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
//				saveUpdateQuafying();
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//					saveUpdateQuafying();
				}
			}

//			private void saveUpdateQuafying() {
//				qualifyingService.saveUpdate(new Qualifying(qualifyingDescriptionTextBox.getText()), new AsyncCallback<Qualifying>() {
//					@Override
//					public void onFailure(Throwable caught) {
//					}
//
//					@Override
//					public void onSuccess(Qualifying result) {
//						Window.Location.reload();
//					}
//				});
//
//			}
		}

		MyHandler handler = new MyHandler();
		saveButton.addClickHandler(handler);
		qualifyingDescriptionTextBox.addKeyUpHandler(handler);

		VerticalPanel vp = new VerticalPanel();
		vp.setBorderWidth(1);
		vp.add(cellTableOfQualifying);
		vp.add(pager);

		RootPanel.get("tableContainer").add(vp);
//		RootPanel.get("nameFieldContainer").add(qualifyingDescriptionTextBox);
//		RootPanel.get("sendButtonContainer").add(saveButton);

	}

	private void openEditPage(int rowIndex, ArrayList<EmployeeLevel> result, boolean isNewEmployee) {
		if (!isNewEmployee) {
			EmployeeLevel selectedEmployee = result.get(rowIndex - 1); // Adjusting index due to header row
//			String id = String.valueOf(selectedEmployee.getId());
//			String version = String.valueOf(selectedEmployee.getVersion());
//			String description = selectedEmployee.getDescription();

			EditEmployeePage editPage = new EditEmployeePage("id", "version", "descrition", isNewEmployee,
					new AsyncCallback<ArrayList<EmployeeLevel>>() {
						@Override
						public void onSuccess(ArrayList<EmployeeLevel> result) {
//							sendNameToServer();
						}

						@Override
						public void onFailure(Throwable caught) {
							caught.printStackTrace();
						}
					});
			editPage.center();
			editPage.show();
		} else {
//			displayInputAndSaveButton();
		}
	}

	private void openSaveUpdateDialog(Qualifying qualifying) {

		GWT.log("openSaveUpdateDialog qualifying= " + qualifying.toString());
		this.openDialogBox(qualifying);
//	    if (insertInputs) {
//            selectedEmployee = result.get(rowIndex - 1);
//            id = String.valueOf(selectedEmployee.getId());
//            version = String.valueOf(selectedEmployee.getVersion());
//            description = selectedEmployee.getDescription();
//        } else {
//            id = version = description = "";
//        }

//		EditEmployeePage editPage = new EditEmployeePage(qualifying);
//		editPage.center();
//		editPage.show();
	}

	private void updateQualifyingDialog(Qualifying qualifying) {

		GWT.log("updateQualifyingDialog qualifying= " + qualifying.toString());
		this.openSaveUpdateDialog(qualifying);
	}

	private void saveUpdateQualifying(Qualifying qualifying) {
		// qualifying
		GWT.log("saveUpdateQualifying= " + qualifying.toString());
		this.qualifyingService.saveUpdate(qualifying, new AsyncCallback<Qualifying>() {

			@Override
			public void onSuccess(Qualifying result) {
				// TODO Auto-generated method stub
				GWT.log(" onSuccess " + result.toString());
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}

	private class MyDialog extends DialogBox {

		public MyDialog(Qualifying qualifying) {

			// Set the dialog box's caption.
//			setText("My First Dialog");

			// Enable animation.
			setAnimationEnabled(true);

			// Enable glass background.
			setGlassEnabled(true);

			// DialogBox is a SimplePanel, so you have to set its widget
			// property to whatever you want its contents to be.
			Button saveBtn = new Button("Save");
			Button cancelBtn = new Button("Cancel");

			TextBox descriptionTextBox = new TextBox();

			saveBtn.addClickHandler(new ClickHandler() {

				public void onClick(ClickEvent event) {
					Qualifying qualifyingTemp = new Qualifying();
					if (qualifying != null) {
						qualifyingTemp = new Qualifying(qualifying);
					}

					qualifyingTemp.setDescription(descriptionTextBox.getText());

					saveUpdateQualifying(qualifyingTemp);

//					GWT.log("descriptionTextBox= " + descriptionTextBox.getText());
					MyDialog.this.hide();

				}

			});

			cancelBtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					MyDialog.this.hide();
				}
			});
			if (qualifying != null) {
				descriptionTextBox.setText(qualifying.getDescription());
			}

			VerticalPanel panel = new VerticalPanel();
			panel.setHeight("800");
			panel.setWidth("800");
			panel.setSpacing(10);
			panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

			panel.add(new Label("Description"));
			panel.add(descriptionTextBox);

			HorizontalPanel btnsPanel = new HorizontalPanel();
			btnsPanel.add(saveBtn);
			btnsPanel.add(cancelBtn);
			panel.add(btnsPanel);

			setWidget(panel);
		}

	}

	private void openDialogBox(Qualifying qualifying) {
		// Instantiate the dialog box and show it.
		MyDialog myDialog = new MyDialog(qualifying);
		int left = Window.getClientWidth() / 2;
		int top = Window.getClientHeight() / 2;
		myDialog.setPopupPosition(left, top);
		myDialog.show();
	}

	private void createDialogBtn() {
		Button b = new Button("NEW");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				openDialogBox(null);
			}
		});

//		RootPanel.get(this.tableContainerId)
		RootPanel.get("dialogBtnId").add(b);
	}

	private void createGrid() {

		CellTable<Qualifying> cellTableOfQualifying = new CellTable<Qualifying>();
		SimplePager pager = new SimplePager();
		pager.setDisplay(cellTableOfQualifying);
		pager.setPageSize(15);
		// The policy that determines how keyboard selection will work. Keyboard
		// selection is enabled.
		cellTableOfQualifying.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		cellTableOfQualifying.setTitle("Qualifying Table");

		// create table column
		TextColumn<Qualifying> columnId = new TextColumn<Qualifying>() {
			@Override
			public String getValue(Qualifying object) {
				return object.getId().toString();
			}
		};
		cellTableOfQualifying.addColumn(columnId, "Id");
		TextColumn<Qualifying> columnVersion = new TextColumn<Qualifying>() {
			@Override
			public String getValue(Qualifying object) {
				return object.getVersion().toString();
			}
		};
		cellTableOfQualifying.addColumn(columnVersion, "Version");
		TextColumn<Qualifying> columnDescription = new TextColumn<Qualifying>() {
			@Override
			public String getValue(Qualifying object) {
				return object.getDescription();
			}
		};
		cellTableOfQualifying.addColumn(columnDescription, "Description");

		// Add edit and delete buttons
		ButtonCell buttonEdit = new ButtonCell();
		Column<Qualifying, String> buttonColumnEdit = new Column<Qualifying, String>(buttonEdit) {
			@Override
			public String getValue(Qualifying object) {
				// The value to display in the button.
				return "Edit";
			}
		};
		cellTableOfQualifying.addColumn(buttonColumnEdit, "Edit");
		// You can then set a FieldUpdater on the Column to be notified of clicks.
		buttonColumnEdit.setFieldUpdater(new FieldUpdater<Qualifying, String>() {
			public void update(int index, Qualifying object, String value) {
				// Value is the button value. Object is the row object.
//				Window.alert("You want to edit object with id: " + object.getId());
				updateQualifyingDialog(object);
			}
		});

		// Create delete function
		ButtonCell buttonDelete = new ButtonCell();
		Column<Qualifying, String> buttonColumnDelete = new Column<Qualifying, String>(buttonDelete) {
			@Override
			public String getValue(Qualifying object) {
				// The value to display in the button.
				return "Delete";
			}
		};
		cellTableOfQualifying.addColumn(buttonColumnDelete, "Delete");
		// You can then set a FieldUpdater on the Column to be notified of clicks.
		buttonColumnDelete.setFieldUpdater(new FieldUpdater<Qualifying, String>() {
			public void update(int index, Qualifying object, String value) {
				// Value is the button value. Object is the row object.
				Window.alert("You want to delete object with id: " + object.getId());
				qualifyingService.delete(object.getId(), new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						Window.alert("Object was deleted.");
						Window.Location.reload();
					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});
			}
		});

		// add selection on rows
		final SingleSelectionModel<Qualifying> selectionModel = new SingleSelectionModel<Qualifying>();
		cellTableOfQualifying.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
			}
		});

		// populate table
		qualifyingService.findAll(new AsyncCallback<ArrayList<Qualifying>>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(ArrayList<Qualifying> result) {
				cellTableOfQualifying.setRowCount(result.size(), true);
				cellTableOfQualifying.setRowData(0, result);
			}
		});

		Button saveButton = new Button("Save");
		saveButton.setStyleName("sendButton");

		TextBox qualifyingDescriptionTextBox = new TextBox();
		// qualifyingDescriptionTextBox.setText("Qualifying description.");
		qualifyingDescriptionTextBox.setFocus(true);

		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				saveUpdateQuafying();
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					saveUpdateQuafying();
				}
			}

			private void saveUpdateQuafying() {
				qualifyingService.saveUpdate(new Qualifying(qualifyingDescriptionTextBox.getText()),
						new AsyncCallback<Qualifying>() {
							@Override
							public void onFailure(Throwable caught) {
							}

							@Override
							public void onSuccess(Qualifying result) {
								Window.Location.reload();
							}
						});

			}
		}

		MyHandler handler = new MyHandler();
		saveButton.addClickHandler(handler);
		qualifyingDescriptionTextBox.addKeyUpHandler(handler);

		VerticalPanel vp = new VerticalPanel();
		vp.setBorderWidth(1);
		vp.add(cellTableOfQualifying);
		vp.add(pager);

//		GWT.log("RootPanel.get tableContainer = " + RootPanel.get(this.tableContainerId));
		if (RootPanel.get(this.tableContainerId) != null) {
			RootPanel.get(this.tableContainerId).add(vp);
		}

//		RootPanel.get("nameFieldContainer").add(qualifyingDescriptionTextBox);
//		RootPanel.get("sendButtonContainer").add(saveButton);

	}

	public void onModuleLoad() {
		this.createDialogBtn();
		this.createGrid();

//		final Button sendButton = new Button("Send");
//		final TextBox nameField = new TextBox();
//		nameField.setText("GWT User");
//		final Label errorLabel = new Label();
//
//		sendButton.addStyleName("sendButton");
//
//		RootPanel.get("nameFieldContainer").add(nameField);
//		RootPanel.get("sendButtonContainer").add(sendButton);
//		RootPanel.get("errorLabelContainer").add(errorLabel);
//
//		nameField.setFocus(true);
//		nameField.selectAll();
//
//		final DialogBox dialogBox = new DialogBox();
//		dialogBox.setText("Remote Procedure Call");
//		dialogBox.setAnimationEnabled(true);
//		final Button closeButton = new Button("Close");
//		closeButton.getElement().setId("closeButton");
//		final Label textToServerLabel = new Label();
//		final HTML serverResponseLabel = new HTML();
//		VerticalPanel dialogVPanel = new VerticalPanel();
//		dialogVPanel.addStyleName("dialogVPanel");
//		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
//		dialogVPanel.add(textToServerLabel);
//		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
//		dialogVPanel.add(serverResponseLabel);
//		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
//		dialogVPanel.add(closeButton);
//		dialogBox.setWidget(dialogVPanel);
//
//		closeButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				dialogBox.hide();
//				sendButton.setEnabled(true);
//				sendButton.setFocus(true);
//			}
//		});
//
//		class MyHandler implements ClickHandler, KeyUpHandler {
//			public void onClick(ClickEvent event) {
//				sendNameToServer();
//			}
//			public void onKeyUp(KeyUpEvent event) {
//				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//					sendNameToServer();
//				}
//			}
//
//			private void sendNameToServer() {
//				// First, we validate the input.
//				errorLabel.setText("");
//				String textToServer = nameField.getText();
//				if (!FieldVerifier.isValidName(textToServer)) {
//					errorLabel.setText("Please enter at least four characters");
//					return;
//				}
//
//				// Then, we send the input to the server.
//				sendButton.setEnabled(false);
//				textToServerLabel.setText(textToServer);
//				serverResponseLabel.setText("");
//
//				employeeLevelService.findAll(new AsyncCallback<ArrayList<EmployeeLevel>>() {
//
//					@Override
//					public void onSuccess(ArrayList<EmployeeLevel> result) {
//
//						GWT.log("public void onSuccess " + result.get(0).toString());
//
//					}
//
//					@Override
//					public void onFailure(Throwable caught) {
//						GWT.log("public void onFailure " + caught.getMessage());
//
//					}
//				});
//
////				greetingService.greetServer(textToServer, new AsyncCallback<String>() {
////					public void onFailure(Throwable caught) {
////						// Show the RPC error message to the user
////						dialogBox.setText("Remote Procedure Call - Failure");
////						serverResponseLabel.addStyleName("serverResponseLabelError");
////						serverResponseLabel.setHTML(SERVER_ERROR);
////						dialogBox.center();
////						closeButton.setFocus(true);
////					}
////
////					public void onSuccess(String result) {
////						GWT.log("public void onSuccess " + result);
////						dialogBox.setText("Remote Procedure Call");
////						serverResponseLabel.removeStyleName("serverResponseLabelError");
////						serverResponseLabel.setHTML(result);
////						dialogBox.center();
////						closeButton.setFocus(true);
////					}
////				});
//			}
//		}
//
//		MyHandler handler = new MyHandler();
//		sendButton.addClickHandler(handler);
//		nameField.addKeyUpHandler(handler);
	}
}
