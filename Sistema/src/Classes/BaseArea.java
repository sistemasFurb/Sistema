/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Conexao.Conexao;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author matheus
 */
public class BaseArea {
    
    private final static int VALOR_MATERIAL_BOM = 50;
    
    private final static int FUNCIONARIOS_12_MESES = 21;
    private final static int MAQUINARIO_12_MESES = 10;
    
    private int valorParcialMetroQuadrado = 0;
    private int valorParcialMaoObra = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition calculoMetroQuadro = lock.newCondition();
    private Condition calculoMaoObra = lock.newCondition();
    private Conexao conexao;
    private HashMap<String, HashMap> parametros = new HashMap();

    public BaseArea(HashMap parametros, Conexao conexao) {
        this.conexao = conexao;
        this.parametros = parametros;
    }

    /**
     * Executa calculo metro quadrado
     */
    public synchronized void calculoMetroQuadrado(int valorAreaCidade)
    {
        HashMap metragem = this.parametros.get("metragem");
        int metragemAp = (int)metragem.get(0);
        valorParcialMetroQuadrado = metragemAp * valorAreaCidade + VALOR_MATERIAL_BOM;
        System.out.println("valor parcialmetro metro quadrado: "+ valorParcialMetroQuadrado);
        calculoMetroQuadro.signalAll();
    }
    
    /**
     * Execute calculo mão de obra
     */
    public synchronized void calculaMaoObra(int valorMaoObra)
    {
        HashMap tempo = this.parametros.get("tempo");
        int tempoObra = (int)tempo.get(0);
        
        if(tempoObra == 6){
            int funcionarios = FUNCIONARIOS_12_MESES/2;
            int maquinario = MAQUINARIO_12_MESES/2;
            valorParcialMaoObra = funcionarios * valorMaoObra + (maquinario * tempoObra);
        } else if(tempoObra == 12){
            valorParcialMaoObra = FUNCIONARIOS_12_MESES * valorMaoObra + (MAQUINARIO_12_MESES * tempoObra);
        } else if(tempoObra == 24){
            int funcionarios = FUNCIONARIOS_12_MESES*2;
            int maquinario = MAQUINARIO_12_MESES*2;
            valorParcialMaoObra = funcionarios * valorMaoObra + (maquinario * tempoObra);
        } 
        System.out.println("valor parcialmetro mao obra: "+ valorParcialMaoObra);
        calculoMaoObra.signalAll();
    }
    
    /**
     * executa calculo em cima do tipo de investimento
     */
    public synchronized void calculaTipoInvestimento() throws InterruptedException
    {
        int valorParcialConstrucao = 0;
        
        while(this.valorParcialMaoObra == 0){
            calculoMaoObra.await();
        }
        
        while(this.valorParcialMetroQuadrado == 0){
            calculoMetroQuadro.await();
        }
        HashMap andares = this.parametros.get("andares");
        int qtdapandar = (int)andares.get(0);
        HashMap apAndar = this.parametros.get("apAndar");
        int qtdandar = (int)apAndar.get(0);
        
        valorParcialConstrucao = (((valorParcialMetroQuadrado+valorParcialMaoObra) * qtdapandar)*qtdandar);
        System.out.println("valor parcial construção: "+valorParcialConstrucao);
    }
    
    /**
     * Calcula tipo de lazer com base no que usuário escolheu como lazer
     */
    public void calculaTipoLazer()
    {
        
    }
    
    /**
     * Executa calcula area comum conforme seleção do usuário
     */
    public void calculoAreaComum()
    {
        
    }
}
