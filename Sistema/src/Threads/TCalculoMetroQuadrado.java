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
public class TCalculoMetroQuadrado extends Thread{
    
    private BaseArea baseArea;
    private int valorCalculoArea = 0;

    public TCalculoMetroQuadrado(BaseArea baseArea, int valorCalculoArea) {
        this.baseArea = baseArea;
        this.valorCalculoArea = valorCalculoArea;
    }

    public void run() {
        try {
            this.baseArea.calculoMetroQuadrado(valorCalculoArea);
        } catch (Exception e) {
            System.out.println("");
        }
    }
}
