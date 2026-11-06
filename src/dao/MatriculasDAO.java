package dao;

import model.Matriculas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MatriculasDAO {
    public final Connection CONEXAO_DB;

    //Construtor que inicializa com o banco de dados
    public MatriculasDAO(Connection CONEXAO_DB){
        this.CONEXAO_DB = CONEXAO_DB;
    }

    //MOSTRAR INFORMAÇÕES DE TODOS OS ALUNOS

    public List<Matriculas> ConsultarInfoAlunos(){
        List<Matriculas> listarInfoAlunos = new ArrayList<>();
        String sql = "SELECT m.id, m.usuario_id , u.nome, u.email, p.plano, p.valor, m.data_inicio, m.data_fim, m.status " +
                "FROM matriculas m " +
                "INNER JOIN usuarios u ON m.usuario_id = u.id " +
                "INNER JOIN planos p ON m.plano_id = p.id;";

        try(PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                Matriculas matricula = new Matriculas();
                matricula.setId(rs.getInt("id"));
                matricula.setUsuario_id(rs.getInt("usuario_id"));
                matricula.setEmail(rs.getString("email"));
                matricula.setPlano(rs.getString("plano"));
                matricula.setValor(rs.getDouble("valor"));
                matricula.setData_inicio(rs.getString("data_inicio"));
                matricula.setData_fim(rs.getString("data_fim"));
                matricula.setStatus(rs.getString("status"));
                listarInfoAlunos.add(matricula);
            }
        }catch (SQLException e){
            System.err.println("Erro ao Consultar Informações de todos os alunos :" + e.getMessage());
        }
        return listarInfoAlunos;
    }

}
