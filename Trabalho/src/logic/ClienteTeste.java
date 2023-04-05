/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package logic;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Virginia
 */
public class ClienteTeste  {
   public static void main(String[] args) 
         throws UnknownHostException, IOException {
     // dispara cliente
     
      ClienteTeste Cliente1 = new ClienteTeste("10.0.0.35", 12345);
      Cliente1.executa(Cliente1);

   }
   
   private String host;
   private int porta;
   public String ip;


    public Socket cliente;
    public Recebedor r;
    public Interface interface1;
   
   public ClienteTeste (String host, int porta) {
     //super("Cliente Chat");
     this.host = host;
     this.porta = porta;
     this.ip = ""; 
   }
   
   public void executa(ClienteTeste cliente1) throws UnknownHostException, IOException {

    this.cliente = new Socket(this.host, this.porta);
     System.out.println("O cliente se conectou ao servidor!");
     this.interface1=new Interface(this.cliente, cliente1);

     this.ip = this.cliente.getLocalAddress().toString().replace("/","");
 
     // thread para receber mensagens do servidor
     this.r = new Recebedor(cliente.getInputStream(), ip, cliente1);
     new Thread(r).start();
     
     // lê msgs do teclado e manda pro servidor
     Scanner teclado = new Scanner(System.in);
     PrintStream saida = new PrintStream(cliente.getOutputStream());

     while (teclado.hasNextLine()) {
       saida.println("Usuário " + ip + " : "  + this.interface1.CampoChat.getText());
       saida.println("Usuário " + ip + " : " + teclado.nextLine());
     }
     
     saida.close();
     teclado.close();
     //cliente.close();    
   }
}


 