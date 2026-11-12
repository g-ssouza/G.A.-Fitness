package dao;

import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final Connection CONEXAO_DB;

    //Construtor Que inicaliza a conexão com o banco de dados;
    public UsuarioDAO(Connection Conexao_DB){
        this.CONEXAO_DB = Conexao_DB;
    }

    //Metodo para inserir usuarios na tabela
    public void inserirUsuarios(Usuario usuario){
        String sql = "INSERT INTO usuarios (nome, email, senha, tipo, telefone, cpf) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipo());
            stmt.setString(5, usuario.getTelefone());
            stmt.setString(6, usuario.getCpf());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuario " + e.getMessage());
        }
    }

    // LOGIN
    public Usuario login(String email, String senha){
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try(PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)){
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return montarUsuario(rs);
                }
            }
        }catch (SQLException e){
            System.err.println("Erro ao fazer login: " + e.getMessage());
        }
        return null;
    }

    //CONSULTAR POR CPF
    public Usuario ConsultarPorCPF(String cpf){
        String sql = "SELECT * FROM usuarios WHERE cpf = ?";
        try(PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)){
            stmt.setString(1, cpf);
            try(ResultSet rs = stmt.executeQuery()){
                stmt.setString(1, cpf);
                if(rs.next()){
                    return montarUsuario(rs);
                }
            }
        }catch (SQLException e){
            System.err.println("Erro ao Consultar por ID : " + e.getMessage());
        }
        return null;
    }

    //Metodo para atualizar senha
    public boolean atualizarSenha(String Novasenha, String cpf){
        String sql = "UPDATE usuarios SET senha = ? WHERE email = ?";
        try(PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)){
            stmt.setString(1, Novasenha);
            stmt.setString(2, cpf);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("erro ao Atualizar Senha: "+  e.getMessage());
            return false;
        }
    }


    //Metodo para listar todos os Usuarios
    public List<Usuario> ListarTodososUsuario(){
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios;";
        try(PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                usuarios.add(montarUsuario(rs));
            }
        }catch (SQLException e){
            System.err.println("Erro ao listar Usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    private Usuario montarUsuario(ResultSet rs) throws SQLException{
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setTipo(rs.getString("tipo"));
        usuario.setTelefone(rs.getString("telefone"));
        usuario.setCpf(rs.getString("cpf"));
        return usuario;
    }
}
