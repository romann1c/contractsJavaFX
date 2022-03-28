package com.romann1c.contracts.database.purejdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {

    private ConnectionFactory connectionFactory;

    public DataBaseManager(ConnectionFactory connectionFactory){
        this.connectionFactory = connectionFactory;
        createDB();
    }

    public void createDB(){
        try{
            Statement statement = connectionFactory.getConnection().createStatement();
            statement.execute("create table if not exists contracts.contracts(\n" +
                    "    id serial primary key,\n" +
                    "    number int unique,\n" +
                    "    contractName varchar(255) not null,\n" +
                    "    dateofsign date not null,\n" +
                    "    dateofupdate date not null\n" +
                    ");\n");
            connectionFactory.getConnection().close();
        } catch (SQLException e){
            System.out.println("Exception");
        }
    }
    public void saveContract(Contract contract){
        try {
            PreparedStatement preparedStatement = connectionFactory.
                    getConnection().
                    prepareStatement("insert into contracts.contracts (number, contractname, dateofsign, dateofupdate) values (?, ?, ?, ?);");
            preparedStatement.setInt(1, contract.getNumber());
            preparedStatement.setString(2, contract.getName()); //
            preparedStatement.setDate(3, contract.getDateOfSign());
            preparedStatement.setDate(4,contract.getDateOfUpdate());
            System.out.println(preparedStatement.toString());
            int rows = preparedStatement.executeUpdate();
            connectionFactory.getConnection().close();
        } catch (SQLException e) {
            System.out.println("Exception while saving " + e);
        }

    }

    public List<Contract> getAllContracts(){
        List<Contract> contracts = new ArrayList<Contract>();
        ResultSet resultSet = null;
            try {
                Statement statement = connectionFactory.getConnection().createStatement();
                resultSet = statement.executeQuery("select * from contracts.contracts");
                while (resultSet.next()){
                            int number = resultSet.getInt("number");
                            String name = resultSet.getString("contractname");
                            Date dateOfSign = resultSet.getDate("dateofsign");
                            Date dateOfUpdate = resultSet.getDate("dateofupdate");
                            Contract contract = new Contract(number, name, dateOfSign, dateOfUpdate);
                            contracts.add(contract);
                }
            } catch (SQLException e) {
                System.out.println("Error while getting contacts");
            }
        return contracts;
    }

}
