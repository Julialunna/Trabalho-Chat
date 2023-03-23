/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author Virginia
 */
public class ClienteChat extends JFrame {
   public static void main(String[] args) 
         throws UnknownHostException, IOException {
     // dispara cliente
      ClienteChat Cliente1 = new ClienteChat("192.168.51.124", 12345);
      Cliente1.setSize(600,600);
      Cliente1.setVisible(true);
      Cliente1.executa();

      Cliente1.AreaDoChat = new JTextArea();
      Cliente1.AreaDoChat.setBackground(Color.WHITE);
      Cliente1.AreaDoChat.setForeground(Color.BLACK);

      JScrollPane chatScroll = new JScrollPane(Cliente1.AreaDoChat);
      chatScroll.setPreferredSize(new Dimension(400, 400));
      Cliente1.AreaDoChat.setFont(new Font("Serif", Font.BOLD, 20));


      Cliente1.CampoChat = new JTextField();
      Cliente1.CampoChat.addActionListener(Cliente1);

   }
   
   private String host;
   private int porta;
   private String ip;

    private JTextArea AreaDoChat;
    private JTextField CampoChat;
    private JButton BotaoEnviar;
   
   public ClienteChat (String host, int porta) {
    super("Cliente Chat");
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
 }
