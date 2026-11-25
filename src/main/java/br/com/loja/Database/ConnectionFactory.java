package br.com.loja.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/loja";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url, user, password);
    }

}
