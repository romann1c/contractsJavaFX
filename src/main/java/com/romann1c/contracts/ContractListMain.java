package com.romann1c.contracts;

import com.romann1c.contracts.database.purejdbc.ConnectionFactory;
import com.romann1c.contracts.database.purejdbc.Contract;
import com.romann1c.contracts.database.purejdbc.DataBaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContractListMain extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ContractListMain.class.getResource("contract-list.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Договоры");
        stage.setScene(scene);
        stage.show();
        List<Contract> contractList = new ArrayList<>();
        DataBaseManager manager = new DataBaseManager(new ConnectionFactory());
        contractList = manager.getAllContracts();
    }

    public static void main(String[] args) {
        launch();
    }
}