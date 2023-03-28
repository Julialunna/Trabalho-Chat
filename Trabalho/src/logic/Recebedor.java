/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Virginia
 */
public class Recebedor implements Runnable {
 
   private InputStream servidor;
   private String ip;
   public ClienteChat cliente1;
 
   public Recebedor(InputStream servidor, String ip, ClienteChat cliente1) {
     this.servidor = servidor;
     this.ip = ip;
     this.cliente1=cliente1;
   }
 
   @Override
   public void run() {
     // recebe msgs do servidor e imprime na tela
     Scanner s = new Scanner(this.servidor);
     int teste=0;
     
     while (s.hasNextLine()) {
        String msg = s.nextLine();
        String[] h = msg.split(" "); 
        for(int i=0;i<h.length;i++){
          System.out.println(h[i]);
        }     
        if(!h[1].equals(this.ip)){
          cliente1.AreaDoChat.append(msg+"\n");
          System.out.println(msg);
        }
        
     }
   }
 }