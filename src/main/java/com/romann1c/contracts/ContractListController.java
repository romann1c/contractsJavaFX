package com.romann1c.contracts;

import com.romann1c.contracts.database.purejdbc.ConnectionFactory;
import com.romann1c.contracts.database.purejdbc.Contract;
import com.romann1c.contracts.database.purejdbc.DataBaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ContractListController {

    DataBaseManager manager = new DataBaseManager(new ConnectionFactory());


    private ObservableList<Contract> contractObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Contract> contractTable;

    @FXML
    private TableColumn<Contract, Integer> contractNumber;

    @FXML
    private TableColumn<Contract, String> contractName;

    @FXML
    private TableColumn<Contract, Date> contractDateOfSign;

    @FXML
    private TableColumn<Contract, Date> contractDateOfUpdate;

    @FXML
    private TableColumn<Contract, Boolean> contractValid;

    @FXML
    private TextField number;

    @FXML
    private TextField nameOfContract;

    @FXML
    private TextField dateOfSignOfContract;

    @FXML
    private TextField dateOfUpdateOfContract;


    @FXML
    private Button registration;


    @FXML
    private void initialize() {

        registration.setOnAction(event -> {
            initialize();
        });

        String numberStr = number.getText();
        numberStr.replaceAll("\\D", "");
        Integer number = null;
        if (numberStr.length() > 0) {
            number = Integer.parseInt(numberStr);
        }

        String dateOfSign = dateOfSignOfContract.getText();

        String dateOfUpdate = dateOfSign;

        String dateOfUpdateTemp = dateOfUpdateOfContract.getText();
        ;
        if (dateOfUpdateTemp.length() > 0) {dateOfUpdate = dateOfUpdateTemp;}

        String name = nameOfContract.getText();

        if (!(number == null)) {
            try {
                Contract contractAdd = new Contract(number, name, dateOfSign, dateOfUpdate);
                manager.saveContract(contractAdd);
            } catch (Exception e) {
                System.out.println(e + " while saving");
            }
        }


        contractObservableList.clear();

        List<Contract> contractList = manager.getAllContracts();
        contractObservableList.addAll(contractList);

        contractNumber.setCellValueFactory(new PropertyValueFactory<Contract, Integer>("number"));
        contractName.setCellValueFactory(new PropertyValueFactory<Contract, String>("name"));
        contractDateOfSign.setCellValueFactory(new PropertyValueFactory<Contract, Date>("dateOfSign"));
        contractDateOfUpdate.setCellValueFactory(new PropertyValueFactory<Contract, Date>("dateOfUpdate"));
        contractValid.setCellValueFactory(new PropertyValueFactory<Contract, Boolean>("valid"));

        contractTable.setItems(contractObservableList);
    }


}

