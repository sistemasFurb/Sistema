/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Conexao.Conexao;

/**
 *
 * @author Master
 */
public class principal {
    public static void main(String[] args) {
        Conexao con = new Conexao();
        con.conectar();
        con.inserir("INSERT INTO CENTRO_FORNECEDOR(CD_CENTRO_FORNEC, DS_CENTRO_FORNEC)"+
                    "VALUES (1,'Teste')");
        con.inserir("INSERT INTO CENTRO_FORNECEDOR(CD_CENTRO_FORNEC, DS_CENTRO_FORNEC)"+
                    "VALUES (2,'Teste')");
    }
}
