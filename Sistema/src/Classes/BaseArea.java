/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author matheus
 */
public class BaseArea {
    
    CalculoCustoObra calculoCusto;

    public BaseArea(CalculoCustoObra calculoCusto) {
        this.calculoCusto = calculoCusto;
    }
    
    public synchronized void calculaCustoObra(){
        
    }
}
