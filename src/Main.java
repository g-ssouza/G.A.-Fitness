import conexao.ConexaoDB;
import dao.MatriculasDAO;
import dao.PlanosDAO;
import dao.TreinoDAO;
import dao.UsuarioDAO;
import model.Matriculas;
import model.Planos;
import model.Treino;
import model.Usuario;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Connection conexao = ConexaoDB.conectar();
        if (conexao == null) {
            System.err.println("Não foi possível iniciar o sistema sem conexão com o banco.");
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        MatriculasDAO matriculasDAO = new MatriculasDAO(conexao);
        PlanosDAO planosDAO = new PlanosDAO(conexao);
        TreinoDAO treinoDAO = new TreinoDAO(conexao);

        int escolha;
        do {
            System.out.println("\nBEM VINDO A G.A.(Geração Ativa) FITNESS");
            System.out.println("1 - Login");
            System.out.println("0 - Sair");
            escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    System.out.print("\nEmail: ");
                    String email = sc.next();
                    System.out.print("Senha: ");
                    String senha = sc.next();

                    Usuario usuario = usuarioDAO.login(email, senha);

                    if (usuario == null) {
                        System.out.println("E-mail ou senha incorretos!");
                        break;
                    }

                    System.out.println("Bem-vindo(a), " + usuario.getNome() + "! Perfil: " + usuario.getTipo());
                    switch (usuario.getTipo()) {
                        case "Gerente":
                            menuGerente(sc, usuarioDAO, matriculasDAO, planosDAO, treinoDAO);
                            break;
                        case "Funcionario":
                            menuFuncionario(sc, treinoDAO);
                            break;
                        case "Aluno":
                            menuAluno(sc, usuario, treinoDAO);
                            break;
                        default:
                            System.out.println("Tipo de usuário desconhecido.");
                    }
                    break;

                case 0:
                    System.out.println("Sessão encerrada");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (escolha != 0);

        try {
            conexao.close();
        } catch (Exception e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
        sc.close();
    }

    // ---------------- MENUS ----------------

    private static void menuGerente(Scanner sc, UsuarioDAO usuarioDAO, MatriculasDAO matriculasDAO,
                                    PlanosDAO planosDAO, TreinoDAO treinoDAO) {
        int escolha;
        do {
            System.out.println("\n--- MENU GERENTE ---");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Consultar usuário por CPF");
            System.out.println("4 - Cadastrar plano");
            System.out.println("5 - Listar matrículas dos alunos");
            System.out.println("6 - Relatório mensal");
            System.out.println("7 - Listar treinos");
            System.out.println("0 - Logout");
            escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    Usuario novo = new Usuario();
                    System.out.print("Nome: ");
                    novo.setNome(sc.nextLine().trim());
                    System.out.print("Email: ");
                    novo.setEmail(sc.nextLine().trim());
                    System.out.print("Senha: ");
                    novo.setSenha(sc.nextLine().trim());
                    System.out.print("Tipo (Gerente/Funcionario/Aluno): ");
                    novo.setTipo(sc.nextLine().trim());
                    System.out.print("Telefone: ");
                    novo.setTelefone(sc.nextLine().trim());
                    System.out.print("CPF: ");
                    novo.setCpf(sc.nextLine().trim());
                    usuarioDAO.inserirUsuarios(novo);
                    System.out.println("Usuário processado.");
                    break;

                case 2:
                    List<Usuario> usuarios = usuarioDAO.ListarTodososUsuario();
                    if (usuarios.isEmpty()) System.out.println("Nenhum usuário cadastrado.");
                    for (Usuario u : usuarios) {
                        System.out.println(u.getId() + "," + u.getNome() + "," + u.getEmail() + "," + u.getTipo() + " , " + u.getTelefone() + " , " + u.getCpf());
                    }
                    break;

                case 3:
                    System.out.print("CPF: ");
                    Usuario encontrado = usuarioDAO.ConsultarPorCPF(sc.nextLine().trim());
                    if (encontrado == null) {
                        System.out.println("Usuário não encontrado.");
                    } else {
                        System.out.println(encontrado.getId() + " | " + encontrado.getNome() + " | " + encontrado.getEmail()
                                + " | " + encontrado.getTipo() + " | " + encontrado.getTelefone());
                    }
                    break;

                case 4:
                    System.out.print("Nome do plano: ");
                    String nomePlano = sc.nextLine().trim();
                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    System.out.print("Duração (dias): ");
                    int dias = sc.nextInt();
                    System.out.print("Status (Ativo/Inativo): ");
                    String status = sc.nextLine().trim();
                    planosDAO.CadastrarPlano(new Planos(nomePlano, valor, dias, status));
                    System.out.println("Plano processado.");
                    break;

                case 5:
                    List<Matriculas> matriculas = matriculasDAO.ConsultarInfoAlunos();
                    if (matriculas.isEmpty()){
                        System.out.println("Nenhuma matrícula encontrada.");
                    }
                    for (Matriculas m : matriculas) {
                        System.out.println(m.getId() + " , " + m.getEmail() + " , " + m.getPlano()
                                + " , R$ " + m.getValor() + " , " + m.getData_inicio()
                                + " -> " + m.getData_fim() + " , " + m.getStatus());
                    }
                    break;

                case 6:
                    Double total = planosDAO.RelatorioMensal();
                    if (total == null) {
                        System.out.println("Não foi possivel Gerar o relatorio");
                    }
                    System.out.printf("Faturamento do mês: R$ %.2f", total);
                    break;

                case 7:
                    listarTreinos(treinoDAO.listarTreinos());
                    break;

                case 0:
                    System.out.println("Saindo do menu do gerente...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (escolha != 0);
    }

    private static void menuFuncionario(Scanner sc, TreinoDAO treinoDAO) {
        int escolha;
        do {
            System.out.println("\n--- MENU FUNCIONÁRIO ---");
            System.out.println("1 - Listar treinos");
            System.out.println("0 - Logout");
            escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    listarTreinos(treinoDAO.listarTreinos());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (escolha != 0);
    }

    private static void menuAluno(Scanner sc, Usuario aluno, TreinoDAO treinoDAO) {
        int escolha;
        do {
            System.out.println("\n--- MENU ALUNO ---");
            System.out.println("1 - Ver meus treinos");
            System.out.println("0 - Logout");
            escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    boolean achou = false;
                    for (Treino t : treinoDAO.listarTreinos()) {
                        if (t.getAlunoId() == aluno.getId()) {
                            imprimirTreino(t);
                            achou = true;
                        }
                    }
                    if (!achou){
                        System.out.println("Você ainda não possui treinos.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (escolha != 0);
    }

    // ---------------- AUXILIARES ----------------

    private static void listarTreinos(List<Treino> treinos) {
        if (treinos.isEmpty()) System.out.println("Nenhum treino cadastrado.");
        for (Treino t : treinos) imprimirTreino(t);
    }

    private static void imprimirTreino(Treino t) {
        System.out.println("#" + t.getId() + " | " + t.getNome() + " | " + t.getDescricao()
                + " | Criado em: " + t.getDataCriacao() + " | " + t.getStatus());
    }
}