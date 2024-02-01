package com.example.newproject.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Newproject implements EntryPoint {

    private VerticalPanel mainVerticalPanel = new VerticalPanel();
    private FlexTable flexTable = new FlexTable();

    public void onModuleLoad() {

        Label titleLabel = new Label("Employee Information");

        String[][] employeeData = { { "John", "Doe", "2023-01-01", "1980-01-15" },
                { "Jane", "Smith", "2023-01-01", "1985-05-20" } };

        flexTable.setStyleName("gwt-Table");

        for (int row = 0; row < employeeData.length; row++) {
            final int currentRow = row; // Declarațiile locale din interiorul buclei for trebuie să fie final sau efectiv final

            HTML editIcon = new HTML("<i class=\"fas fa-pencil-alt\"></i> ");
            Button editButton = new Button();
            editButton.getElement().appendChild(editIcon.getElement());

            editButton.addClickHandler(event -> openEditPage(currentRow));
            flexTable.setWidget(row + 1, 0, editButton);
        }

        flexTable.setText(0, 1, "First name");
        flexTable.setText(0, 2, "Last name");
        flexTable.setText(0, 3, "Hire date");
        flexTable.setText(0, 4, "Birth date");

        for (int row = 0; row < employeeData.length; row++) {
            for (int col = 0; col < employeeData[row].length; col++) {
                flexTable.setText(row + 1, col + 1, employeeData[row][col]);
            }
        }

        flexTable.getRowFormatter().addStyleName(0, "headerRow");
        flexTable.setWidth("100%");
        titleLabel.getElement().getStyle().setMarginBottom(10, com.google.gwt.dom.client.Style.Unit.PX);

        this.mainVerticalPanel.add(titleLabel);
        this.mainVerticalPanel.add(flexTable);

        RootPanel.get().add(this.mainVerticalPanel);
    }

    private void openEditPage(int rowIndex) {
        // Obține datele utilizatorului și deschide pagina de editare
        String firstName = flexTable.getText(rowIndex + 1, 1);
        String lastName = flexTable.getText(rowIndex + 1, 2);
        String hireDate = flexTable.getText(rowIndex + 1, 3);
        String birthDate = flexTable.getText(rowIndex + 1, 4);

        // Deschide pagina de editare cu datele utilizatorului
        EditEmployeePage editPage = new EditEmployeePage(firstName, lastName, hireDate, birthDate, this);
        mainVerticalPanel.clear(); // Șterge doar conținutul din mainVerticalPanel
        mainVerticalPanel.add(editPage);
    }

    public void showOriginalContent() {
        mainVerticalPanel.clear(); // Șterge doar conținutul din mainVerticalPanel
        onModuleLoad(); // Afișează conținutul original
    }
}
