package com.romann1c.contracts;

import com.romann1c.contracts.database.ConnectionFactory;
import com.romann1c.contracts.database.Contract;
import com.romann1c.contracts.database.DataBaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
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
    private TextField numberOfContract;

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

        //перезапускаем метод при нажатии кнопки.
        registration.setOnAction(event -> {
            initialize();
        });

        //Считываем строку из приложения, переводим её в число.
        Integer number = this.strToNum(numberOfContract.getText());
        //Получаем название договора.
        String name = nameOfContract.getText();
        //Получаем дату заключения.
        String dateOfSign = dateOfSignOfContract.getText();
        //Получаем дату внесения изменений, либо присваиваем значение даты заключения.
        String dateOfUpdate = getDateOfUpdate(dateOfSign, dateOfUpdateOfContract.getText());
        //Создаем объект и добавляем в БД.
        createAndAddContract(number, name, dateOfSign, dateOfUpdate);

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

    private Integer strToNum(String inputNum){
        inputNum.replaceAll("\\D", "");
        Integer number = null;
        if (inputNum.length() > 0) {
            number = Integer.parseInt(inputNum);
        }
        return number;
    }

    private void createAndAddContract(Integer number, String name, String dateOfSign, String dateOfUpdate){
        if (!(number == null)) {
            try {
                Contract contractAdd = new Contract(number, name, dateOfSign, dateOfUpdate);
                manager.saveContract(contractAdd);
            } catch (Exception e) {
                System.out.println(e + " while saving");
            }
        }
    }

    private String getDateOfUpdate(String dateOfSign, String dateOfUpdateTemp){
        String dateOfUpdate = dateOfSign;
        if (dateOfUpdateTemp.length() > 0) dateOfUpdate = dateOfUpdateTemp;
        return dateOfUpdate;
    }
}

