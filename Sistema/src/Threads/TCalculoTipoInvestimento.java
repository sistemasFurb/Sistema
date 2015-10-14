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
public class TCalculoTipoInvestimento extends Thread{

    private BaseArea baseArea;

    public TCalculoTipoInvestimento(BaseArea baseArea) {
        this.baseArea = baseArea;
    }

    public void run() {
        try {
            this.baseArea.calculaTipoInvestimento();
        } catch (Exception e) {
            System.out.println("");
        }
    }
}
