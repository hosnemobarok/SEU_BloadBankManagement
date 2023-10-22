package com.example.seu_bloadbankmanagement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.util.List;
import java.util.ArrayList;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;


public class HelloController implements Initializable {
    // MARK: - Outlet
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private ChoiceBox<BloodGroup> bloadGroupChoiceBox;

    @FXML
    private ChoiceBox<BloodGroup> searchChoiceBox;

    @FXML
    private Button submitButton;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private Label showMessageLable;

    @FXML
    private TableView<PersonModel> dataTableView;

    @FXML
    private TableColumn<PersonModel, String> nameTvColumn;

    @FXML
    private TableColumn<PersonModel, Integer> ageTvColumn;

    @FXML
    private TableColumn<PersonModel, BloodGroup> bloadGroupTvColumn;

    @FXML
    private TableColumn<PersonModel, String> addressTvColumn;

    @FXML
    private TableColumn<PersonModel, String> phoneNumberTvColumn;

    @FXML
    private TableColumn<PersonModel, Integer> quantityTvColumn;

    @FXML
    private ObservableList<PersonModel> personModelObservableList;

    @FXML
    ObservableList<PersonModel> personList = FXCollections.observableArrayList();

    // MARK: - Life Cycle
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize columns
        nameTvColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        ageTvColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getAge()).asObject());
        addressTvColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getAddress()));
        phoneNumberTvColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPhoneNumber()));
        quantityTvColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getQuantity()).asObject());

        if (bloadGroupTvColumn != null) {
            bloadGroupTvColumn.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getBloodGroup()));
        }

        ObservableList<BloodGroup> bloodGroupOptions = FXCollections.observableArrayList(
                BloodGroup.A_POSITIVE, BloodGroup.A_NEGATIVE,
                BloodGroup.B_POSITIVE, BloodGroup.B_NEGATIVE,
                BloodGroup.AB_POSITIVE, BloodGroup.AB_NEGATIVE,
                BloodGroup.O_POSITIVE, BloodGroup.O_NEGATIVE
        );

        // Set the options to the choice box
        bloadGroupChoiceBox.setItems(bloodGroupOptions);
        searchChoiceBox.setItems(bloodGroupOptions);

        // Optionally, you can set a default value if needed
        bloadGroupChoiceBox.setValue(BloodGroup.A_POSITIVE);
        //searchChoiceBox.setValue(BloodGroup);

        // Initialize data (replace this with your actual data source)
        personModelObservableList = FXCollections.observableArrayList();
        dataTableView.setItems(personModelObservableList);


        if (personModelObservableList.isEmpty()) {
            // If it's empty, set the submit button title to "Add"
            submitButton.setText("ADD");
        }

    }

    // MARK: - Private Methods
    private void clearInputFields() {
        nameTextField.clear();
        ageTextField.clear();
        bloadGroupChoiceBox.setValue(BloodGroup.A_POSITIVE);
        addressTextField.clear();
        phoneNumberTextField.clear();
        quantityTextField.clear();
    }

    // MARK: - Button Action
    @FXML
    protected void submitButtonAction() {
        try {
            // Get values from UI components
            String name = nameTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            // Assuming you have a method to get the selected BloodGroup from the checkbox
            BloodGroup bloodGroup = bloadGroupChoiceBox.isShowing() ? bloadGroupChoiceBox.getValue() : BloodGroup.A_POSITIVE;
            String address = addressTextField.getText();
            String phoneNumber = phoneNumberTextField.getText();
            int quantity = Integer.parseInt(quantityTextField.getText());

            // Create a new PersonModel object
            PersonModel newPerson = new PersonModel(name, age, bloodGroup, address, phoneNumber, quantity, false);

            // Add the new person to the ObservableList
            personModelObservableList.add(newPerson);

            // Clear input fields after adding
            clearInputFields();

            // Optionally, you can show a success message or perform other actions
            //showMessageLable.setText("Successfully added!");
            showMessage("Successfully added!");
            submitButton.setText("ADD");
        } catch (NumberFormatException e) {
            // Handle the case where parsing of age or quantity fails
            showMessage("Please enter valid age and quantity.");
            //showMessageLable.setText("Please enter valid age and quantity.");
        }
    }

    protected void showMessage(String message) {
        showMessageLable.setText(message);

        // Set up a timeline to clear the message after 5 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMessageLable.setText("");
            }
        }));
        timeline.play();
    }


    @FXML
    protected void searchButtonAction() {
        BloodGroup selectedBloodGroup = searchChoiceBox.getValue();

        List<PersonModel> searchResults = new ArrayList<>();

        for (PersonModel person : personList) {
            if (selectedBloodGroup == null || person.getBloodGroup() != null && person.getBloodGroup().equals(selectedBloodGroup)) {
                searchResults.add(person);
            }
        }

        // Clear existing data in the table
        dataTableView.getItems().clear();

        // Add the search results to the table
        dataTableView.getItems().addAll(searchResults);
    }


    public void deleteButtonAction() {
        PersonModel selectedProduct = dataTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            personModelObservableList.remove(selectedProduct);
            showMessage("Successfully deleted!");
        } else {
            // Handle the case where no product is selected
            showMessage("No row selected to delete.");
        }
    }

    @FXML
    protected void onTableSelect() {
        System.err.println("A ROW IS SELECTED");
        submitButton.setText("UPDATE");
        // Get the selected row
        PersonModel selectedPerson = dataTableView.getSelectionModel().getSelectedItem();

        if (selectedPerson != null) {
            // Print the data for the selected row
            System.out.println("Name: " + selectedPerson.getName());
            System.out.println("Age: " + selectedPerson.getAge());
            System.out.println("Blood Group: " + selectedPerson.getBloodGroup());
            System.out.println("Address: " + selectedPerson.getAddress());
            System.out.println("Phone Number: " + selectedPerson.getPhoneNumber());
            System.out.println("Quantity: " + selectedPerson.getQuantity());

            // Set the layout data for the fields
            nameTextField.setText(selectedPerson.getName());
            ageTextField.setText(Integer.toString(selectedPerson.getAge()));
            bloadGroupChoiceBox.setValue(selectedPerson.getBloodGroup());
            addressTextField.setText(selectedPerson.getAddress());
            phoneNumberTextField.setText(selectedPerson.getPhoneNumber());
            quantityTextField.setText(Integer.toString(selectedPerson.getQuantity()));
        }
    }


}