/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Classes.Base;
import Classes.BaseArea;

/**
 *
 * @author matheus
 */
public class TArea extends Thread{
    BaseArea baseArea;

    public TArea(BaseArea baseArea) {
        this.baseArea = baseArea;
    }

    public void run() {
        baseArea.calculaCustoObra();
    }
}
