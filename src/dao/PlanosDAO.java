package dao;

import model.Planos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PlanosDAO {
    private final Connection CONEXAO_DB;

    //Construtor que inicializa com o banco de dados

    public PlanosDAO(Connection CONEXAO_DB){
        this.CONEXAO_DB = CONEXAO_DB;
    }

    //CADASTRAR PLANO
    public int cadastrarPlano(Planos plano) {
        String sql = "INSERT INTO planos (usuario_id, plano, valor, duracao_dias, status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, plano.getUsuario_id());
            stmt.setString(2, plano.getPlano());
            stmt.setDouble(3, plano.getValor());
            stmt.setInt(4, plano.getDuracao_dias());
            stmt.setString(5, plano.getStatus());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar plano: " + e.getMessage());
        }
        return -1;
    }

    //Relátorio Mensal
    public Double RelatorioMensal(){
        String sql = "SELECT SUM(valor) FROM planos p " +
                "INNER JOIN matriculas m ON p.id = m.plano_id " +
                "WHERE MONTH(m.data_inicio) = MONTH(CURRENT_DATE());";
        try(PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            if (rs.next()){
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.err.println("Erro no relatorio Mensal: " + e.getMessage());
        }
        return null;
    }

}
