package com.romann1c.contracts;

import com.romann1c.contracts.database.purejdbc.ConnectionFactory;
import com.romann1c.contracts.database.purejdbc.Contract;
import com.romann1c.contracts.database.purejdbc.DataBaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ContractListController {

    DataBaseManager manager = new DataBaseManager(new ConnectionFactory());
    List<Contract> contractList = manager.getAllContracts();

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
    private void initialize(){

        contractObservableList.addAll(contractList);

        contractNumber.setCellValueFactory(new PropertyValueFactory<Contract, Integer>("number"));
        contractName.setCellValueFactory(new PropertyValueFactory<Contract, String>("name"));
        contractDateOfSign.setCellValueFactory(new PropertyValueFactory<Contract, Date>("dateOfSign"));
        contractDateOfUpdate.setCellValueFactory(new PropertyValueFactory<Contract, Date>("dateOfUpdate"));
        contractValid.setCellValueFactory(new PropertyValueFactory<Contract, Boolean>("valid"));

        contractTable.setItems(contractObservableList);
    }
}

