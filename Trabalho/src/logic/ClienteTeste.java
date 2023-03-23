/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.*;
import java.net.*;
import java.util.Scanner;

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
public class ClienteTeste extends JFrame implements KeyListener, ActionListener {
   public static void main(String[] args) 
         throws UnknownHostException, IOException {
     // dispara cliente

     JFrame ChatJFrame = new JFrame("Chat");
     ChatJFrame.setSize(600,600);


     JTextArea AreaDoChat=new JTextArea();
     AreaDoChat.setBackground(Color.BLACK);
     AreaDoChat.setEditable(false);
     AreaDoChat.setForeground(Color.BLACK);
     AreaDoChat.setVisible(true);
    
     JScrollPane chatScroll = new JScrollPane(AreaDoChat);

     JPanel Painel = new JPanel(new BorderLayout());
     Painel.add(chatScroll, BorderLayout.CENTER);
     ChatJFrame.add(Painel);

     JPanel PainelInferior = new JPanel(new BorderLayout());

     JTextField CampoChat=new JTextField(20);
    
     JButton BotaoEnviar= new JButton("Enviar");
     BotaoEnviar.setSize(10,5);
     BotaoEnviar.addActionListener(BotaoEnviar);
     
     PainelInferior.add(CampoChat, BorderLayout.CENTER);
     PainelInferior.add(BotaoEnviar, BorderLayout.EAST);

     Painel.add(PainelInferior, BorderLayout.SOUTH);

     AreaDoChat.setFont(new Font("Serif", Font.BOLD, 20));
     
     ChatJFrame.setVisible(true);

     new Cliente("192.168.51.124", 12345).executa();
   }

   
   
   private String host;
   private int porta;
   private String ip;
   
   public ClienteTeste (String host, int porta) {
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

       saida.println("Usuário " + ip + " : " + teclado.nextLine());

     }
     
     saida.close();
     teclado.close();
     cliente.close();    
   }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
  }
 }
