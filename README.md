<div align="center"> <img src="assets/banner.svg" alt="G.A. Fitness - Geração Ativa" width="100%"/> <br/>

Gerencie alunos, planos, matrículas e treinos — direto do terminal.

<br/>

Mostrar Imagem Mostrar Imagem Mostrar Imagem Mostrar Imagem Mostrar Imagem

<br/>

Sobre · Funcionalidades · Terminal · Banco de dados · Como rodar · Documentação · Roadmap

</div> <br/>
💜 Sobre

O G.A. Fitness é um sistema de console que organiza o dia a dia de uma academia: usuários, planos, matrículas e treinos, tudo guardado em MySQL e acessado por Java + JDBC.

Cada pessoa vê apenas o que importa para a sua função, com menus diferentes para Gerente, Funcionário e Aluno.

[!NOTE] Este projeto é o trabalho de conclusão de semestre do grupo. O código segue o padrão DAO, separando modelo, acesso ao banco e interface.

<br/>
🔥 Funcionalidades
<table> <tr> <td align="center" width="33%"> <h3>👑 Gerente</h3> <sub>Controle total</sub> <br/><br/> Cadastrar e listar usuários<br/> Consultar por CPF<br/> Cadastrar planos<br/> Listar matrículas<br/> Relatório mensal<br/> Listar treinos </td> <td align="center" width="33%"> <h3>🧑‍🏫 Funcionário</h3> <sub>Acompanhamento</sub> <br/><br/> Listar treinos<br/> <br/> <i>Em breve: criar e<br/>editar treinos</i> </td> <td align="center" width="33%"> <h3>🏃 Aluno</h3> <sub>Rotina de treino</sub> <br/><br/> Ver os próprios treinos<br/> <br/> <i>Em breve: ver plano<br/>e vencimento</i> </td> </tr> </table>

🔐 Login seguro por e-mail e senha — após entrar, o sistema abre automaticamente o menu do perfil.

<br/>
🖥️ No terminal
text
╔══════════════════════════════════════════╗
║   BEM VINDO A G.A. (Geração Ativa)       ║
║               F I T N E S S              ║
╚══════════════════════════════════════════╝
  1 - Login
  0 - Sair

  > 1
  Email: admin@gafitness.com
  Senha: ********

  ✔ Bem-vindo(a), Admin! Perfil: Gerente

  ─── MENU GERENTE ───────────────────────
  1 - Cadastrar usuário
  2 - Listar usuários
  3 - Consultar usuário por CPF
  4 - Cadastrar plano
  5 - Listar matrículas dos alunos
  6 - Relatório mensal
  7 - Listar treinos
  0 - Logout

  > 6
  Faturamento do mês: R$ 1500,00
🔀 Fluxo do sistema
não
sim
não
sim
Gerente
Funcionário
Aluno
▶ Início
Conexão com o banco?
⛔ Encerra
Menu inicial
Login: e-mail e senha
Credenciais válidas?
Perfil do usuário
👑 Menu do Gerente
🧑‍🏫 Menu do Funcionário
🏃 Menu do Aluno
<br/>
🗄️ Banco de dados
possui
define
aluno recebe
funcionário cria
USUARIOS
int
id
PK
varchar
nome
varchar
email
varchar
senha
enum
tipo
Gerente, Funcionario, Aluno
varchar
telefone
varchar
cpf
MATRICULAS
int
id
PK
int
usuario_id
FK
int
plano_id
FK
date
data_inicio
date
data_fim
enum
status
Ativa, Cancelada, Concluida
PLANOS
int
id
PK
varchar
plano
decimal
valor
int
duracao_dias
enum
status
Ativo, Inativo
TREINOS
int
id
PK
int
aluno_id
FK
int
funcionario_id
FK
varchar
nome
text
descricao
date
data_criacao
enum
status
Ativo, Inativo
<br/>
🧱 Arquitetura
text
GAFitness/
├── 🔌 conexao/
│   ├── ConexaoDB.java         ➜ conexão com o MySQL
│   └── Tabela.java            ➜ cria as tabelas
├── 📦 model/
│   ├── Usuario.java
│   ├── Planos.java
│   ├── Matriculas.java
│   └── Treino.java
├── 🗃️ dao/
│   ├── UsuarioDAO.java
│   ├── PlanosDAO.java
│   ├── MatriculasDAO.java
│   └── TreinoDAO.java
├── 📄 docs/
│   └── Documentacao_GA_Fitness.docx
└── 🚀 Main.java               ➜ login e menus por perfil
Camada	Papel
📦 model	Guarda os dados: atributos, getters e setters
🗃️ dao	Executa o SQL e devolve objetos do model
🚀 Main	Conversa com o usuário e chama os DAOs
<br/>
🚀 Como rodar

[!IMPORTANT] Você precisa do JDK 17+, do MySQL 8 em execução e do MySQL Connector/J adicionado ao projeto.

1️⃣  Crie o banco

sql
CREATE DATABASE gafitness;

2️⃣  Configure a conexão em ConexaoDB.java

java
public static final String URL_JDBC = "jdbc:mysql://localhost:3306/gafitness?allowMultiQueries=true";
public static final String user  = "seu_usuario";
public static final String senha = "sua_senha";

[!WARNING] Nunca suba sua senha real do MySQL para o GitHub.

3️⃣  Adicione o driver JDBC — inclua o mysql-connector-j (.jar) nas bibliotecas do projeto. Sem ele aparece o erro "No suitable driver found".

4️⃣  Crie as tabelas — execute Tabela.java uma vez. Você deve ver:

text
Tabelas Criadas com Sucesso

5️⃣  Crie o primeiro Gerente — só um Gerente cadastra os outros usuários, então o primeiro entra direto pelo banco:

sql
USE gafitness;
INSERT INTO usuarios (nome, email, senha, tipo, telefone, cpf)
VALUES ('Admin', 'admin@gafitness.com', 'admin123', 'Gerente', '11999999999', '000.000.000-00');

6️⃣  Execute Main.java e entre com o Gerente criado. 💪

<details> <summary><b>🩹 Problemas comuns e soluções</b></summary> <br/>
Erro	Solução
No suitable driver found	Adicione o mysql-connector-j ao projeto
NullPointerException ao conectar	A conexão falhou: confira URL, usuário, senha e se o MySQL está ligado
Table already exists	Apague as tabelas ou use CREATE TABLE IF NOT EXISTS
Unknown database 'gafitness'	Rode CREATE DATABASE gafitness;
Non-static method ... static context	Chame pelo objeto: usuarioDAO.login(email, senha)
</details> <br/>
📄 Documentação

O projeto acompanha uma documentação completa em Word, feita pelo grupo para o trabalho de conclusão de semestre.

	
📑 Arquivo	Documentacao_GA_Fitness.docx (troque pelo nome real)
📂 Pasta	docs/
📘 Conteúdo	Introdução, requisitos, modelagem, arquitetura, testes e conclusão
<br/>
🛣️ Roadmap
 Conexão com MySQL
 Criação das tabelas
 Login por e-mail e senha
 Menus por perfil (Gerente, Funcionário e Aluno)
 Cadastro e listagem de usuários
 Cadastro de planos e relatório mensal
 Documentação do trabalho em Word
 Matricular aluno em um plano
 Criar e editar treinos
 Alterar senha pelo menu
 Criptografar senhas com hash
 Página HTML/CSS que simula o terminal
<br/>
🛠️ Tecnologias
<div align="center">

Mostrar Imagem Mostrar Imagem Mostrar Imagem Mostrar Imagem Mostrar Imagem

</div> <br/>
<div align="center">
💪 G.A. Fitness — Geração Ativa

Treine. Evolua. Conecte.

<sub>Feito com ☕ e muito código pelo grupo · 2026</sub>

</div>
