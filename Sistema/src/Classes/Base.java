/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Conexao.Conexao;
import Threads.TArea;
import Threads.Tcidade;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author matheus
 */
public class Base {
    private HashMap<String,HashMap> parametros = new HashMap();
    Conexao conexao;
    BaseArea baseArea;
    CalculoCustoObra calculoCustoObra;
    
    public Base(HashMap parametros, Conexao conexao) 
    {
        this.parametros = parametros;
        this.conexao = conexao;
        this.calculoCustoObra = new CalculoCustoObra();
        this.baseArea = new BaseArea(this.calculoCustoObra);
    }
    
    /**
     * Inicializa threads de cidade conforme filtro selecionado pelo usuário
     */
    public void inicializaTCidade()
    {
        //Verifica quantidade de cidades selecionadas pelo usuário
        int quantidadeCidades = this.getQtdCidades(this.parametros.get("cidade"));
        
        //Chama threads para cada cidade selecionada
        for(int i = 0; i < quantidadeCidades; i++){
            Tcidade threadCidade = new Tcidade((HashMap)this.parametros.get("cidade").get(i), this);
            threadCidade.start();
        }
    }
    
    /**
     * Verifica quantidade de cidades selecionadas pelo usuário 
     */
    private int getQtdCidades(HashMap parametroCidade)
    {
        return parametroCidade.size();
    }
    
    /**
     * Verifica quantidade de areas da cidade e inicializa threads
     */
    public synchronized void inicializaTArea()
    {
        int quantidadeAreaCidade = this.getAreasCidade();
        
        for(int i = 0;i < quantidadeAreaCidade; i++){
            TArea threadArea = new TArea(this.baseArea);
            threadArea.start();
        }
    }
    
    /**
     * Retorna quantidade de areas da cidade
     */
    public int getAreasCidade()
    {
        //Retorna quantidade de areas da cidade
        this.conexao.select("",null);
        return 0;
        
    }
}
