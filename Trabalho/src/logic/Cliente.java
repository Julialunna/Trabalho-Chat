/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Virginia
 */
public class Cliente {
   public static void main(String[] args) 
         throws UnknownHostException, IOException {
     // dispara cliente
     new Cliente("10.0.0.16", 12345).executa();
   }
   
   private String host;
   private int porta;
   private String ip;
   
   public Cliente (String host, int porta) {
     this.host = host;
     this.porta = porta;
     this.ip = "";
   }
   
   public void executa() throws UnknownHostException, IOException {
     Socket cliente = new Socket(this.host, this.porta);
     System.out.println("O cliente se conectou ao servidor!");

     this.ip = cliente.getLocalAddress().toString().replace("/","");
 
     // thread para receber mensagens do servidor
     Recebedor r = new Recebedor(cliente.getInputStream(), ip);
     new Thread(r).start();
     
     // lê msgs do teclado e manda pro servidor
     Scanner teclado = new Scanner(System.in);
     PrintStream saida = new PrintStream(cliente.getOutputStream());

     while (teclado.hasNextLine()) {

       saida.println("Usuário " + ip + ": " + teclado.nextLine());

     }
     
     saida.close();
     teclado.close();
     cliente.close();    
   }
 }
