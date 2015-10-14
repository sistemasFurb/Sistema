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
import java.util.HashMap;

/**
 *
 * @author Master
 */
public class principal {
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        conexao.conectar();
        
        HashMap<String, HashMap> filtros = new HashMap();
        HashMap<Integer, Integer> filtrosCidade = new HashMap();
        HashMap<Integer, Integer> filtrosAndares = new HashMap();
        HashMap<Integer, Integer> filtrosMetragem = new HashMap();
        HashMap<Integer, Integer> filtrosApAndar = new HashMap();
        HashMap<Integer, Integer> filtrosTempo = new HashMap();
        
        filtrosCidade.put(0, 1);
        filtrosAndares.put(0, 4);
        filtrosMetragem.put(0, 75);
        filtrosApAndar.put(0, 3);
        filtrosTempo.put(0, 12);
        
        filtros.put("cidades", filtrosCidade);
        filtros.put("andares", filtrosAndares);
        filtros.put("metragem", filtrosMetragem);
        filtros.put("apAndar", filtrosApAndar);
        filtros.put("tempo", filtrosTempo);
        
        Base base = new Base(filtros, conexao);
        
        base.inicializaTCidade();
    }
}
