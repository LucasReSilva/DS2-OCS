/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop.util;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author tharlysson breno
 */
public class ConexaoMySQL 
{
    //Nome do usuário do mysql
    private final String USERNAME = "desenvolvimento";
    //Senha do mysql
    private final String PASSWORD = "ds123";
    //Dados de caminho, porta e nome da base de dados que irá ser feita a conexão
    private final String DATABASE_URL = "jdbc:mysql://tharlysson.com:3306/opencarshop";
    /**
    * Cria uma conexão com o banco de dados MySQL utilizando o nome de usuário e senha fornecidos
    * @param username
    * @param senha
    * @return uma conexão com o banco de dados
    * @throws Exception
    */
    public Connection conectar() throws Exception
    {
       Class.forName("com.mysql.jdbc.Driver"); //Faz com que a classe seja carregada pela JVM
       //Cria a conexão com o banco de dados
       Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
       return connection;
    }
}