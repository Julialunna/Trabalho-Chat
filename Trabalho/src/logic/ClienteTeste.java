/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class ClienteTeste extends JFrame implements KeyListener, ActionListener, Runnable {
   public static void main(String[] args) 
         throws UnknownHostException, IOException {
     // dispara cliente
      ClienteTeste Cliente1 = new ClienteTeste("192.168.15.78", 12345);
      Cliente1.ChatJFrame = new JFrame("Chat");
      Cliente1.ChatJFrame.setSize(600,600);


      Cliente1.AreaDoChat = new JTextArea();
      Cliente1.AreaDoChat.setBackground(Color.WHITE);
      Cliente1.AreaDoChat.setEditable(false);
      Cliente1.AreaDoChat.setForeground(Color.BLACK);
      Cliente1.AreaDoChat.setVisible(true);

      JScrollPane chatScroll = new JScrollPane(Cliente1.AreaDoChat);

      Cliente1.Painel = new JPanel(new BorderLayout());
      Cliente1.Painel.add(chatScroll, BorderLayout.CENTER);
      Cliente1.ChatJFrame.add(Cliente1.Painel);

      Cliente1.PainelInferior = new JPanel(new BorderLayout());

      Cliente1.CampoChat = new JTextField(20);

      Cliente1.BotaoEnviar = new JButton("Enviar");
      Cliente1.BotaoEnviar.setSize(10,5);
      Cliente1.BotaoEnviar.addActionListener(Cliente1);
  

      Cliente1.PainelInferior.add(Cliente1.CampoChat, BorderLayout.CENTER);
    
      Cliente1.AreaDoChat.setFont(new Font("Serif", Font.BOLD, 20));
      Cliente1.PainelInferior.add(Cliente1.BotaoEnviar, BorderLayout.EAST);


      Cliente1.Painel.add(Cliente1.PainelInferior, BorderLayout.SOUTH);

     
      Cliente1.CampoChat.addKeyListener(Cliente1);

      Cliente1.ChatJFrame.add(Cliente1.Painel);
      Cliente1.AreaDoChat.setFont(new Font("Serif", Font.BOLD, 20));
      Cliente1.ChatJFrame.setVisible(true);
      Cliente1.executa(Cliente1);

   }
   
   private String host;
   private int porta;
   private String ip;

    private JTextArea AreaDoChat;
    private JTextField CampoChat;
    private JButton BotaoEnviar;
    public JPanel Painel;
    public JPanel PainelInferior;
    public JFrame ChatJFrame;

    public Socket cliente;
    public Recebedor r;
   
   public ClienteTeste (String host, int porta) {
    super("Cliente Chat");
     this.host = host;
     this.porta = porta;
     this.ip = "";
   }
   
   public void executa(ClienteTeste cliente1) throws UnknownHostException, IOException {
    this.cliente = new Socket(this.host, this.porta);
     System.out.println("O cliente se conectou ao servidor!");

     this.ip = this.cliente.getLocalAddress().toString().replace("/","");
 
     // thread para receber mensagens do servidor
     this.r = new Recebedor(cliente.getInputStream(), ip);
     new Thread(r).start();
     new Thread(cliente1).start();
     
     // lê msgs do teclado e manda pro servidor
     Scanner teclado = new Scanner(System.in);
     PrintStream saida = new PrintStream(cliente.getOutputStream());

     while (teclado.hasNextLine()) {
       saida.println("Usuário " + ip + " : "  + cliente1.CampoChat.getText());
       saida.println("Usuário " + ip + " : " + teclado.nextLine());

     }
     
     saida.close();
     teclado.close();
     //cliente.close();    
   }


  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      PrintStream saida =  new PrintStream(this.cliente.getOutputStream());
      if(e.getSource()==BotaoEnviar){
        String msg = CampoChat.getText();
        AreaDoChat.append("Você: "+msg+"\n");
        saida.println("Usuário " + this.ip + " : "+msg);
      }
      //saida.close();
    } catch (IOException e1) {
      System.out.println(e1);
    }
  }
  
  @Override
  public void run() {
    // TODO Auto-generated method 
    System.out.println("entrei RUN");
    for(int j=0;j<this.r.buffer.size();j++){
      System.out.println("depois entrou no run:");
      System.out.println(this.r.buffer.get(j));
    }
    while(true){
      if(this.r.buffer.size()>0){
        System.out.println("entrei tem buffer");
        for(int i=0;i<this.r.buffer.size();i++){
          System.out.println(i+this.r.buffer.get(i));
          AreaDoChat.append(this.r.buffer.get(i));
          this.r.buffer.remove(i);
          for(int j=0;j<this.r.buffer.size();j++){
            System.out.println("depois que mandou:");
            System.out.println(this.r.buffer.get(j));
          }
        }
      }
    }
  }
  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
  }

 }
