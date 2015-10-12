/**
 * classe que contém calculos do custo da obra
 */
package Classes;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author matheus
 */
public class CalculoCustoObra {

    private ReentrantLock lock = new ReentrantLock();
    private Condition calculoMetroQuadro = lock.newCondition();
    private Condition calculoMaoObra = lock.newCondition();
    
    public CalculoCustoObra() {
    }
    
    
    /**
     * Executa calculo metro quadrado
     */
    public void calculoMetroQuadrado()
    {
        calculoMetroQuadro.signalAll();
    }
    
    /**
     * Execute calculo mão de obra
     */
    public void calculaMaoObra()
    {
        calculoMaoObra.signalAll();
    }
    
    /**
     * executa calculo em cima do tipo de investimento
     */
    public void calculaTipoInvestimento() throws InterruptedException
    {
        calculoMaoObra.await();
        calculoMetroQuadro.await();
        
        
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
