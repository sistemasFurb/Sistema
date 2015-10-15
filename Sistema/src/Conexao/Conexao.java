package Conexao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 *
 * @author Master
 */
public class Conexao {
    
    Connection con;
    String banco = "analiseinvestimento";
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
    
    public void select(String sql, String[] coluns){
        ArrayList result = new ArrayList();
        
        try {
            Statement s = this.con.createStatement();
            ResultSet r = s.executeQuery(sql);
            while(r.next()){
                for(String value: coluns){
                    result.add(r.getString(value));
                    System.out.println(r.getString(value));
                }
            }
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
    
    public int qtdeAreasCidade(int codigo){
        try{
            String sql = "SELECT COUNT(*) TOTAL FROM AREAVALORES WHERE idCidade = "+codigo;
            Statement s = this.con.createStatement();
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
               return r.getInt("TOTAL");
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }        
        return 0;
    }
    
    public HashMap getAreasCidade(int codigoCidade){
        HashMap<Integer, ArrayList> response  = new HashMap();
        try{
            String sql = "SELECT dsArea as area, valorArea as valor FROM AREAVALORES JOIN AREAS ON AREAS.idArea = AREAVALORES.idArea WHERE AREAVALORES.idCidade = "+codigoCidade;
            Statement s = this.con.createStatement();
            ResultSet r = s.executeQuery(sql);
            
            int indexResponse = 0;
            while(r.next()){
               int indexPreResponse = 0; 
               ArrayList preResponse = new ArrayList();
               preResponse.add(indexPreResponse, r.getString("area"));
               indexPreResponse++;
               preResponse.add(indexPreResponse, r.getInt("valor"));
               response.put(indexResponse, preResponse);
               indexResponse++;
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }        
        return response;
    }
    
    public int getValorMaoObraCidade(int codigoCidade){
        try{
            String sql = "SELECT valorMaoObra as valor FROM maoobravalores WHERE idCidade = "+codigoCidade;
            Statement s = this.con.createStatement();
            ResultSet r = s.executeQuery(sql);
            
            int indexResponse = 0;
            if(r.next()){
               return r.getInt("valor");
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }        
        return 0;
    }
    
    public double valorMQuadrado(int cidade, int area){
        try{
            String sql = "select sum(a.valorArea + b.valorMaterial) valorMquadrado " +
                         "from areavalores a " +
                         "join materialvalores b on b.idAreaValor = a.idAreaValores " +
                         "where a.idcidade = "+cidade+" " +
                         "and a.idArea = "+area+" "+
                         "and b.idmaterial = 1 ";
            Statement s = this.con.createStatement();
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
               return r.getDouble("valorMquadrado");
            }            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return 0;
    }
    
    public double valorMediaHistoricaConstrucao(int cidade, int area){
        try{
            String sql = "select AVG(vlconstrucao) media " +
                         "from imovelhistorico a " +
                         "inner join imovelarea b on (b.idimovelarea = a.idimovelareahistorico) " +
                         "inner join areavalores c on (c.idareavalores = b.idareavalores) "+
                         "where c.idcidade = "+cidade+" " +
                         "and c.idArea = "+area+"";
            Statement s = this.con.createStatement();
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
               return r.getDouble("media");
            } 
        }catch(Exception ex){
             ex.printStackTrace();
        }
        return 0;
    }

}
