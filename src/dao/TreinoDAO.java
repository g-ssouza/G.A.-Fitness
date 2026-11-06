package dao;

import model.Treino;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class TreinoDAO {
    private final Connection CONEXAO_DB;

    public TreinoDAO(Connection CONEXAO_DB){
        this.CONEXAO_DB = CONEXAO_DB;
    }

    //CRIAR TREINO
    public ArrayList<Treino> listarTreinos() {

        ArrayList<Treino> treinos = new ArrayList<>();

        String sql = "SELECT t.*, " +
                "a.nome AS aluno, " +
                "f.nome AS funcionario " +
                "FROM treinos t " +
                "INNER JOIN usuarios a ON t.aluno_id = a.id " +
                "INNER JOIN usuarios f ON t.funcionario_id = f.id";

        try (PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){


            while (rs.next()) {

                Treino treino = new Treino();

                treino.setId(rs.getInt("id"));
                treino.setAlunoId(rs.getInt("aluno_id"));
                treino.setFuncionarioId(rs.getInt("funcionario_id"));
                treino.setNome(rs.getString("nome"));
                treino.setDescricao(rs.getString("descricao"));
                treino.setDataCriacao(rs.getString("data_criacao"));
                treino.setStatus(rs.getString("status"));

                treinos.add(treino);
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return treinos;
    }

}
