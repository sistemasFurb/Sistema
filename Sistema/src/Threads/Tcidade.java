/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Classes.Base;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus
 */
public class Tcidade extends Thread{
    
    private Base base;
    private int cidade;
    
    public Tcidade(int cidade, Base base) {
        this.cidade = cidade;
        this.base = base;
    }

    public void run() {
        try {
            base.inicializaCalculoArea(cidade);
        } catch (InterruptedException e) {
            System.out.println("Erro ao iniciar calculo area "+e);
        }
    }
    
}
