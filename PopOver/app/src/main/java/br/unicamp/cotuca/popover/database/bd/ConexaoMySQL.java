package br.unicamp.cotuca.popover.database.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Victória
 */
public class ConexaoMySQL {

    public String status = "Não conectou...";
    private Statement comando;
    private Connection conexao = null;

    public ConexaoMySQL() throws Exception {
        try {
            // Carregando o JDBC Driver padrão 
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            String serverName = "regulus.mysql.uhserver.com"; //caminho do servidor do BD
            String mydatabase = "regulus"; //nome do seu banco de dados
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "popover"; //nome de um usuário de seu BD
            String password = "mrv-270397"; //sua senha de acesso
            conexao = DriverManager.getConnection(url, username, password);

            if (conexao != null) {
                status = ("STATUS--->Conectado com sucesso!");
                try {
                    comando = conexao.createStatement();
                } catch (SQLException e) {
                    throw new Exception("comando");
                }
            } else {
                status = ("STATUS--->Não foi possivel realizar conexão");
            }
        } catch (ClassNotFoundException e) {
            throw new Exception("O driver especificado nao foi encontrado.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Nao foi possivel conectar ao Banco de Dados.");

        }
    }

    public String statusConection() {
        return status;
    }

    public ConexaoMySQL ReiniciarConexao() throws Exception {
        this.fecharConexao();
        return new ConexaoMySQL();
    }

    public void execComando(String cmdSQL) throws Exception {
        try {
            this.comando.executeUpdate(cmdSQL);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("execucao comando");
        }
    }

    public ResultSet execConsulta(String qrySQL) throws Exception {
        ResultSet resultado = null;

        try {
            resultado = this.comando.executeQuery(qrySQL);
        } catch (SQLException e) {
            throw new Exception("execucao consulta");
        }

        return resultado;
    }

    public void fecharConexao() throws Exception {
        try {
            this.comando.close();
            this.comando = null;

            this.conexao.close();
            this.conexao = null;
        } catch (SQLException e) {
            throw new Exception("fechamento");
        }
    }
}
