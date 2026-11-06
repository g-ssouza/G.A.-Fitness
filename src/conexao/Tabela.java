package conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Tabela {
    public static void main(String[] args) {
        try (Connection conexao = ConexaoDB.conectar();
             Statement stmt = conexao.createStatement()) {


            //Comando Para criar as Tabelas

            String comandoSQL = "CREATE TABLE usuarios(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "nome VARCHAR(100) NOT NULL," +
                    "email VARCHAR(100) NOT NULL," +
                    "senha VARCHAR(255) NOT NULL," +
                    "tipo ENUM('Gerente', 'Funcionario', 'Aluno') NOT NULL," +
                    "telefone VARCHAR(20)," +
                    "cpf VARCHAR(14) NOT NULL);" +

                    "CREATE TABLE planos (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "plano VARCHAR(150) NOT NULL, " +
                    "valor DECIMAL(10,2) NOT NULL," +
                    "duracao_dias INT, " +
                    "status ENUM('Ativo', 'Inativo'));" +

                    "CREATE TABLE matriculas (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "usuario_id INT NOT NULL," +
                    "plano_id INT NOT NULL," +
                    "data_inicio DATE NOT NULL," +
                    "data_fim DATE NOT NULL," +
                    "status ENUM('Ativa', 'Cancelada', 'Concluida') DEFAULT 'Ativa'," +
                    "FOREIGN KEY (usuario_id) REFERENCES usuarios(id)," +
                    "FOREIGN KEY (plano_id) REFERENCES planos(id)" +
                    ");" +

                    "CREATE TABLE treinos (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "aluno_id INT NOT NULL," +
                    "funcionario_id INT NOT NULL," +
                    "nome VARCHAR(100) NOT NULL," +
                    "descricao TEXT NOT NULL," +
                    "data_criacao DATE DEFAULT (CURRENT_DATE)," +
                    "status ENUM('Ativo', 'Inativo') DEFAULT 'Ativo'," +
                    "FOREIGN KEY (aluno_id) REFERENCES usuarios(id)," +
                    "FOREIGN KEY (funcionario_id) REFERENCES usuarios(id));";


            // Executar o Comando
            stmt.execute(comandoSQL);
            System.out.println("Tabelas Criadas com Sucesso");

        } catch (SQLException e) {
            System.err.println("Erro ao criar Tabelas" + e.getMessage());
        }
    }
}
