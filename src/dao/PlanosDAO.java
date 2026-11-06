package dao;

import model.Planos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanosDAO {
    private final Connection CONEXAO_DB;

    //Construtor que inicializa com o banco de dados

    public PlanosDAO(Connection CONEXAO_DB){
        this.CONEXAO_DB = CONEXAO_DB;
    }

    //CADASTRAR PLANO
    public void CadastrarPlano(Planos plano){
        String sql = "INSERT INTO planos (plano, valor, duracao_dias, status) VALUES "+
                "(?, ?, ?, ?);";
        try(PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)){
            stmt.setString(1, plano.getPlano());
            stmt.setDouble(2, plano.getValor());
            stmt.setInt(3, plano.getDuracao_dias());
            stmt.setString(4, plano.getStatus());

            stmt.executeUpdate();
        }catch (SQLException e){
            System.err.println("Erro ao cadastrar Plano: " + e.getMessage());
        }
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
