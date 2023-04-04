




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

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

    public JTextArea AreaDoChat;
    private JTextField CampoChat;
    private JButton BotaoEnviar;
    public JPanel Painel;
    public JPanel PainelInferior;
    public JFrame ChatJFrame;

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


  /*@Override
  public void actionPerformed(ActionEvent e) {
    try {
      PrintStream saida =  new PrintStream(this.cliente.getOutputStream());
      if(e.getSource()==BotaoEnviar){
        String msg = CampoChat.getText();
        AreaDoChat.append("Você: "+msg+"\n");
        saida.println("Usuário " + this.ip + " : "+msg);
        CampoChat.setText("");
      }

      //saida.close();
    } catch (IOException e1) {
      System.out.println(e1);
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_ENTER){

      try {
        PrintStream saida =  new PrintStream(this.cliente.getOutputStream());
        
        String msg = CampoChat.getText();
        AreaDoChat.append("Você: "+msg+"\n");
        saida.println("Usuário " + this.ip + " : "+msg);
        CampoChat.setText("");
        //saida.close();

      } catch (IOException e1) {
        System.out.println(e1);
      }

    }
    // TODO Auto-generated method stub
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
  }
 }*/