/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author Virginia
 */
public class Recebedor implements Runnable {
 
   private InputStream servidor;
   private String ip;
 
   public Recebedor(InputStream servidor, String ip) {
     this.servidor = servidor;
     this.ip = ip;
   }
 
   @Override
   public void run() {
     // recebe msgs do servidor e imprime na tela
     Scanner s = new Scanner(this.servidor);
     
     while (s.hasNextLine()) {
        String msg = s.nextLine();
        String[] h = msg.split(" ");        

        if(!h[1].equals(this.ip)){
          System.out.println(msg);
        }
        
     }
   }
 }