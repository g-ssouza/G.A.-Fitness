package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    public static final String URL_JDBC = "jdbc:mysql://localhost:3306/gafitness?allowMultiQueries=true";
    public static final String user = "root"; //TROCAR POR SENAI
    public static final String senha = "****"; // TROCAR PELA SENHA DO SENAI

    //Conectar com o MYSQL

    public static Connection conectar(){
        try {
            return DriverManager.getConnection(URL_JDBC, user, senha);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o Banco de dados : " + e.getMessage());
            return null;
        }
    }
}
