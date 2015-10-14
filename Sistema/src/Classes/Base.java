/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Conexao.Conexao;
import Threads.TCalculoMaoObra;
import Threads.TCalculoMetroQuadrado;
import Threads.TCalculoTipoInvestimento;
import Threads.Tcidade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

/**
 *
 * @author matheus
 */
public class Base {
    private HashMap<String, HashMap> parametros = new HashMap();
    Conexao conexao;
    BaseArea baseArea;
    private static Semaphore semaforo = new Semaphore(1, true);
    
    public Base(HashMap parametros, Conexao conexao) 
    {
        this.parametros = parametros;
        this.conexao = conexao;
        this.baseArea = new BaseArea(this.parametros, this.conexao);
    }
    
    /**
     * Inicializa threads de cidade conforme filtro selecionado pelo usuário
     */
    public void inicializaTCidade()
    {
        //Verifica quantidade de cidades selecionadas pelo usuário
        HashMap cidades = this.parametros.get("cidades");
        int quantidadeCidades = this.getQtdCidades(cidades);
        
        //Chama threads para cada cidade selecionada
        for(int i = 0; i < quantidadeCidades; i++){
            int codigoCidade = (Integer)cidades.get(i);
            Tcidade threadCidade = new Tcidade(codigoCidade, this);
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
    public void inicializaCalculoArea(int codigoCidade) throws InterruptedException
    {
        HashMap areasCidade = conexao.getAreasCidade(codigoCidade);
        int valorMaoObraCidade = conexao.getValorMaoObraCidade(codigoCidade);
        int quantidadeAreaCidade = areasCidade.size();
        
        for(int i = 0;i < quantidadeAreaCidade; i++){
            semaforo.acquire();
            ArrayList area = (ArrayList)areasCidade.get(i);
            TCalculoMetroQuadrado calculoMetro2 = new TCalculoMetroQuadrado(this.baseArea, (int)area.get(1));
            calculoMetro2.start();
            TCalculoMaoObra calculoMaoObra = new TCalculoMaoObra(this.baseArea, valorMaoObraCidade);
            calculoMaoObra.start();
            TCalculoTipoInvestimento calculoTipoInvestimento = new TCalculoTipoInvestimento(this.baseArea);
            calculoTipoInvestimento.start();
            semaforo.release();
        }
    }

    public Conexao getConexao() {
        return conexao;
    }
    
}
