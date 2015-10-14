/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Classes.BaseArea;

/**
 *
 * @author matheus
 */
public class TCalculoMaoObra extends Thread{
    
    private BaseArea baseArea;
    private int valorMaoObra = 0;

    public TCalculoMaoObra(BaseArea baseArea, int valorMaoObra) {
        this.baseArea = baseArea;
        this.valorMaoObra = valorMaoObra;
    }

    public void run() {
        try {
            this.baseArea.calculaMaoObra(this.valorMaoObra);
        } catch (Exception e) {
            System.out.println("");
        }
    }
    
}
