/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Classes.Base;
import Conexao.Conexao;
import Threads.Tcidade;
import java.util.ArrayList;

/**
 *
 * @author Master
 */
public class principal {
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        conexao.conectar();
        Base base = new Base(null, conexao);
        
        base.inicializaTCidade();
    }
}
