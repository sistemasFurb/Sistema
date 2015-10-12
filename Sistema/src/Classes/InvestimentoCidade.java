/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Threads.TPesquisaHistoricaConstrucao;
import Threads.TPesquisaHistoricaVenda;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author matheus
 */
public class InvestimentoCidade {

    private TPesquisaHistoricaConstrucao pesquisaHistoricaConstrucao;
    private TPesquisaHistoricaVenda pesquisaHistoricaVenda;
    private ReentrantLock lock = new ReentrantLock();
    private Condition calculoMetroQuadro = lock.newCondition();
    private Condition calculoMaoObra = lock.newCondition();
    private static Semaphore semaforo = new Semaphore(3, true);
    
    public void executaCalculosCidade(HashMap filtros) throws InterruptedException{
        try {
            semaforo.acquire();
            calculoCustoObra();
            pesquisaHistoricaConstrucao = new TPesquisaHistoricaConstrucao();
            pesquisaHistoricaVenda = new TPesquisaHistoricaVenda();
            pesquisaHistoricaConstrucao.start();
            pesquisaHistoricaVenda.start();
            semaforo.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private static void calculoCustoObra(){
        //calculoMetroQuadrado
        
        //calculoMaoObra
        // fazer lock para verificar se os dois valores acima já foram calculados
    }
    
    public void calculoMetroQuadrado(){
        
    }
    
    public void calculoMaoObra(){
        
    }
}
