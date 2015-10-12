/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Classes.CalculoObraCidade;

/**
 *
 * @author matheus
 */
public class TCalculoMetroQuadrado extends Thread{
    
    private CalculoObraCidade calculoObra;

    public TCalculoMetroQuadrado(CalculoObraCidade calculoObra) {
        this.calculoObra = calculoObra;
    }

    public void run() {
        try {
            this.calculoObra.calculoMetroQuadrado();
        } catch (Exception e) {
            
        }
    }
    
    
}
