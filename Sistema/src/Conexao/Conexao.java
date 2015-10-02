package Conexao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

/**
 *
 * @author Master
 */
public class Conexao {
    
    Connection con;
    String banco = "teste";
    String url = "jdbc:mysql://localhost/"+this.banco;
    String driver = "com.mysql.jdbc.Driver";
    String user = "root";
    String password = "";
    
    public Conexao() {}

    public void conectar(){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível encontrar o Driver!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível conectar ao banco!");
        } catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Não foi possível conectar!");
        }
    }
    
    public void inserir(String sql){
        try {
            Statement s = this.con.createStatement();
            int r = s.executeUpdate(sql);           
        }catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }

    }

    public void desconectar(){
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
