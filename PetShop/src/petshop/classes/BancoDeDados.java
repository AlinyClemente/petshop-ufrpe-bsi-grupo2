package petshop.classes;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

/**
 * Classe de selação do sistema com banco de dados, as atividades de cadastrar, pesquisar,
 * alterar e excluir cliente, protudos, serviços e animais são todas feitas atravez dessa
 * classe.
 * @author dola
 **/
public abstract class BancoDeDados {

    /**Endereço do Banco de Dados**/
    private static final String DATABASE_URL = "jdbc:mysql://localhost/petshop";
    /**Variavel de conexao com o banco de dados**/
    public static Connection connection = null;
    /**Varivel de execução de comandos SQL no banco de dados**/
    public static Statement statement = null;
    //**varivel de comando sql pre configurados**//
    public static PreparedStatement preparedStatement = null;
    /**Variavel que recebe o resultado das pesquisas do banco de dados**/
    public static ResultSet resultset = null;
    /**Variavel de recebe os metadados das pesquisas no banco de dados. **/
    public static ResultSetMetaData resultsetmetadata = null;
    /**Variavel de status da conecxão**/
    public static boolean conectStatus = false;

    /**Metodo para conectar o sistema ao banco de dados.**/
    public static void conectar() {
        try {
            BancoDeDados.connection = DriverManager.getConnection(DATABASE_URL,
                    "root",
                    "lima1807");
            BancoDeDados.statement = BancoDeDados.connection.createStatement();
            System.out.println("Conectado");
            BancoDeDados.conectStatus = true;


        } catch (SQLException e) {
            System.out.println("Não foi possivel conectar ao banco de dados");
        }
    }

    private static Cliente gerarClienteFromResultset(ResultSet resultset) {

        try {
            Cliente cliente = new Cliente();
            cliente.setCodigo((Integer) resultset.getObject(1));
            cliente.setNome((String) resultset.getObject(2));
            cliente.setSexo((String)resultset.getObject(3));
            CPF cpf = new CPF((String) resultset.getObject(4));
            cliente.setCpf(cpf);
            cliente.setRg((Integer) resultset.getObject(5));
            //Enderero do cliente
            String rua = (String) resultset.getObject(6);
            int n = (Integer) resultset.getObject(7);
            String bairro = (String) resultset.getObject(8);
            String cidade = (String) resultset.getObject(9);
            String complemento = (String) resultset.getObject(10);
            String uf = (String) resultset.getObject(11);
            String cep = (String) resultset.getObject(14);
            //
            Endereco endereco = new Endereco();
            endereco.setRua(rua);
            endereco.setNum(n);
            endereco.setBairro(bairro);
            endereco.setCidade(cidade);
            endereco.setComplemento(complemento);
            endereco.setUf(uf);
            endereco.setCep(cep);
            cliente.setEndereco(endereco);
            //
            cliente.setTelefone((String) resultset.getObject(12));
            cliente.setCelular((String) resultset.getObject(13));
            cliente.setInformacoes((String) resultset.getObject(15));
            return cliente;
        } catch (SQLException e) {
            e.printStackTrace();

            Cliente cliente = new Cliente();
            return cliente;
        }
    }

    private static Produto gerarProdutoFromResultset(ResultSet resultset) {
        try {
            Produto produto = new Produto();
            produto.setCodigo((Integer) resultset.getObject(1));
            produto.setNome((String) resultset.getObject(2));
            produto.setQtdeEstoque((Integer) resultset.getObject(3));
            produto.setPrecoCusto((Double) resultset.getObject(4));
            produto.setPrecoVenda((Double) resultset.getObject(5));
            produto.setInformacoes((String) resultset.getObject(6));
            return produto;
        }catch (SQLException e){
            e.printStackTrace();
            Produto produto = new Produto();
            return produto;

        }

    }

    /**Metodo publico que cadastra um cliente no banco de dados.
     *
     * @param cliente
     */
    public static boolean cadastrar(Cliente cliente) {
        try {
            // Configuração de pre-comando
            preparedStatement = connection.prepareStatement("INSERT INTO cliente(nome,"
                    + "sexo,cpf,rg,rua,ncasa,bairro,cidade,complemento,uf,telefone,"
                    + "celular,cep,info) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            // Entrada de valores
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, String.valueOf(cliente.getSexo()));
            preparedStatement.setString(3, cliente.getCpf().getCpf());
            preparedStatement.setLong(4, cliente.getRg());
            preparedStatement.setString(5, cliente.getEndereco().getRua());
            preparedStatement.setInt(6, cliente.getEndereco().getNum());
            preparedStatement.setString(7, cliente.getEndereco().getBairro());
            preparedStatement.setString(8, cliente.getEndereco().getCidade());
            preparedStatement.setString(9, cliente.getEndereco().getComplemento());
            preparedStatement.setString(10, cliente.getEndereco().getUf());
            preparedStatement.setString(11, cliente.getTelefone());
            preparedStatement.setString(12, cliente.getCelular());
            preparedStatement.setString(13, cliente.getEndereco().getCep());
            preparedStatement.setString(14, cliente.getInformacoes());
            //Execução de comando
            int cod = preparedStatement.executeUpdate();

            System.out.println("ID de cliente " + Integer.toString(cod));
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro no cadastro de Cliente");
            return false;
        }
    }

    /**
     * Metodo publico que cadastra um pruduto no banco de dados.
     *
     * @param produto
     */
    public static boolean cadastrar(Produto produto) {

        try {
            // Configuração de pre-comando
            preparedStatement = connection.prepareStatement("INSERT INTO produtos "
                    + "(codigo,nome,qt,preco_custo,preco_venda,info) VALUES (?,?,?,?,?,?)");
            //Entrada de valores
            preparedStatement.setLong(1, produto.getCodigo());
            preparedStatement.setString(2, produto.getNome());
            preparedStatement.setInt(3, produto.getQtdeEstoque());
            preparedStatement.setDouble(4, produto.getPrecoCusto());
            preparedStatement.setDouble(5, produto.getPrecoVenda());
            preparedStatement.setString(6, produto.getInformacoes());
            // Executando comando SQL
            preparedStatement.executeUpdate();
            //
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastra produto.");
            return false;
        }

    }

    /**
     * Metodo publico que cadastra um servico no banco de dados.
     *
     * @param servico
     **/
    public static boolean cadastrar(Servico servico) {
        try {
            // Pre-comando SQL
            preparedStatement = connection.prepareStatement("INSERT INTO produtos "
                    + "(codigo,nome,duracao,preco,info) VALUES (?,?,?,?,?)");
            //Configuração das varieaveis
            preparedStatement.setLong(1, servico.getCodigo());
            preparedStatement.setString(2, servico.getNome());
            preparedStatement.setInt(3, (int) servico.getDuracao().getTime());
            preparedStatement.setDouble(4, servico.getPrecoVenda());
            preparedStatement.setString(5, servico.getInfo());
            //Execução de comando SQL
            preparedStatement.executeUpdate();
            //
            System.out.println("Servico cadastrado com exito.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastra o serviço.");
            return false;
        }
    }

    public static Cliente[] consultar(Cliente c) {
        int contador = 0;
        int nRegistros = 0;
        try {
            if (!c.getNome().equals("")) {
                preparedStatement = connection.prepareStatement("SELECT * FROM cliente "
                        + "WHERE nome LIKE ?");
                preparedStatement.setString(1, "%" + c.getNome() + "%");
                resultset = preparedStatement.executeQuery();
            }

            resultsetmetadata = resultset.getMetaData();
            while (resultset.next()) {
                nRegistros = nRegistros + 1;
            }
            Cliente[] clienteList = new Cliente[nRegistros];
            resultset.beforeFirst();
            while (resultset.next()) {
                //Contador de cliente
                contador = contador + 1;

                clienteList[contador - 1] = gerarClienteFromResultset(resultset);
            }
            return clienteList;

        } catch (SQLException e) {
            e.printStackTrace();
            Cliente[] clienteList = new Cliente[0];
            return clienteList;
        }
    }

    /**
     * Metodo que realiza uma consulta de produtos no banco de dados a partir
     * de produto com algumas informações.
     *
     * @param Produto
     * @return Produto[]
     **/
    public static Produto[] consultar(Produto p) {
        int contador = 0;
        int nRegistro = 0;
        try {
            if (!p.getNome().equals("")) {
                preparedStatement = connection.prepareStatement("SELECT * FROM "
                        + "produtos WHERE nome LIKE ?");
                preparedStatement.setString(1, "%" + p.getNome() + "%");
                resultset = preparedStatement.executeQuery();
            }
            while (resultset.next()) {
                nRegistro++;
            }
            Produto[] produtoList = new Produto[nRegistro];
            resultset.beforeFirst();
            while (resultset.next()) {
                contador++;                
                produtoList[contador - 1] = gerarProdutoFromResultset(resultset);
            }
            return produtoList;
        } catch (SQLException e) {
            e.printStackTrace();
            Produto[] produtoList = new Produto[0];
            return produtoList;
        }
    }

    /**
     * Medoto que realiza uma consulta de servicos no banco de dados a partir
     * de um servico com algumas informações.
     *
     * @param Servico
     * @return Servico[]
     **/
    public Servico[] consultar(Servico s) {
        int contador = 0;
        int nRegistro = 0;
        try {
            if (!s.getNome().equals("")) {
                preparedStatement = connection.prepareStatement("SELECT * FROM servicos "
                        + "WHERE nome LIKE ?");
                preparedStatement.setString(1, "%" + s.getNome() + "%");
                resultset = preparedStatement.executeQuery();
            }
            while (resultset.next()) {
                nRegistro++;
            }

            Servico[] servicoList = new Servico[nRegistro];
            resultset.beforeFirst();

            while (resultset.next()) {
                contador++;
                Servico servico = new Servico();
                servico.setCodigo((Integer) resultset.getObject(1));
                servico.setNome((String) resultset.getObject(2));
                Date tempo = new Date();
                tempo.setTime((Long)resultset.getObject(3));
                servico.setDuracao(tempo);
                servico.setPrecoVenda((Double) resultset.getObject(4));
                servico.setInfo((String) resultset.getObject(6));

                servicoList[contador - 1] = servico;
            }
            return servicoList;

        } catch (SQLException e) {
            e.printStackTrace();
            Servico[] servicoList = new Servico[0];
            return servicoList;
        }
    }

    /**
     * Metodo alterar dados de clientes no banco de dados
     *
     * @param Cliente
     * @return boolean
     **/
    public static boolean alterar(Cliente cliente) {
        try {
            // Preparação de commando SQL para atualização de registros
            preparedStatement = connection.prepareStatement("UPDATE INTO cliente "
                    + "(nome,sexo,cpf,rg,rua,ncasa,bairro,cidade,complemento,uf,telefone,celular,cep,info)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) WHERE codigo=?");
            // introdução das variaveis no comando
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, String.valueOf(cliente.getSexo()));
            preparedStatement.setString(3, cliente.getCpf().getCpf());
            preparedStatement.setLong(4, cliente.getRg());
            preparedStatement.setString(5, cliente.getEndereco().getRua());
            preparedStatement.setInt(6, cliente.getEndereco().getNum());
            preparedStatement.setString(7, cliente.getEndereco().getBairro());
            preparedStatement.setString(8, cliente.getEndereco().getCidade());
            preparedStatement.setString(9, cliente.getEndereco().getComplemento());
            preparedStatement.setString(10, cliente.getEndereco().getUf());
            preparedStatement.setString(11, cliente.getTelefone());
            preparedStatement.setString(12, cliente.getCelular());
            preparedStatement.setString(13, cliente.getEndereco().getCep());
            preparedStatement.setString(14, cliente.getInformacoes());
            // Codigo do cliente que ira ser alterado
            preparedStatement.setInt(15, cliente.getCodigo());
            //Executa alteraçõe no banco de dados
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Metodo para altera os dados de produtos no banco de dados.
     *
     * @param Produto
     * @return boolean
     **/
    public static boolean alterar(Produto produto) {
        try {
            // Configuração de pre-comando
            preparedStatement = connection.prepareStatement("UPDATE INTO produtos "
                    + "(nome,qt,preco_custo,preco_venda,info) VALUES (?,?,?,?,?) "
                    + "WHERE codigo=?");
            //Entrada de valores
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setInt(2, produto.getQtdeEstoque());
            preparedStatement.setDouble(3, produto.getPrecoCusto());
            preparedStatement.setDouble(4, produto.getPrecoVenda());
            preparedStatement.setString(5, produto.getInformacoes());
            // Codico do produto que ira ser alterado
            preparedStatement.setLong(6, produto.getCodigo());
            // Executando comando SQL
            preparedStatement.executeUpdate();
            // Resultado
            System.out.println("Alteração do produto realizada com exito.");
            return true;

            // Tratamento de erro
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao altera produto.");
            return false;
        }

    }

    /**
     * Metodo para altera dados de um serviço no banco de dados.
     *
     * @param Servico
     **/
    public static boolean alterar(Servico servico) {
        try {
            // Pre-comando SQL
            preparedStatement = connection.prepareStatement("UPDATE INTO produtos "
                    + "(nome,duracao,preco,info) VALUES (?,?,?,?) "
                    + "WHERE codigo=?");
            //Configuração das varieaveis
            preparedStatement.setString(1, servico.getNome());
            preparedStatement.setInt(2, (int) servico.getDuracao().getTime());
            preparedStatement.setDouble(3, servico.getPrecoVenda());
            preparedStatement.setString(4, servico.getInfo());
            // Codido do servico que ira ser alterado
            preparedStatement.setLong(5, servico.getCodigo());
            //Execução de comando SQL
            preparedStatement.executeUpdate();
            // Resultado
            System.out.println("Servico alterado com exito.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao altera o serviço.");
            return false;
        }
    }

    /**Metodo privado que executa comandos SQL a partir de outros metodos
     * no banco de dados e retorna o resultado para a variavel resultset.
     **/
    private static void ExecuteSQLCmd(String cmd) {
        try {
            int result = BancoDeDados.statement.executeUpdate(cmd);
            System.out.print(result);
        } catch (SQLException erro) {
            System.out.print("Não foi possivel executa consulta.");
        }
    }

    public static Animal[] consultar(Animal animal) {
        return new Animal[0];
    }

    public static boolean remover(Animal animal) {
        return false;
    }
}