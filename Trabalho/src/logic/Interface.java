package logic;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.io.*;
import java.net.*;
import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class Interface extends JFrame implements KeyListener, ActionListener {

    public static JTextArea AreaDoChat;
    public static JTextField CampoChat;
    public static JButton BotaoEnviar;
    public static JPanel Painel;
    public static JPanel PainelSuperior;
    public static JPanel PainelInferior;
    public static JFrame ChatJFrame;
    public static Socket cliente;
    public static ClienteTeste cliente1;
    
    public Interface(Socket cliente, ClienteTeste cliente1){

      this.cliente=cliente;
      this.cliente1=cliente1;

      ChatJFrame = new JFrame("Chat");
      ChatJFrame.setSize(400,700);
      //ChatJFrame.setBackground(Color.BLUE);

      AreaDoChat = new JTextArea();
      AreaDoChat.setBackground(Color.WHITE);
      AreaDoChat.setEditable(false);
      AreaDoChat.setForeground(Color.BLACK);
      AreaDoChat.setVisible(true);



      JScrollPane chatScroll = new JScrollPane(AreaDoChat);
      AreaDoChat.setLineWrap(true);

      /*PainelSuperior = new JPanel(new BorderLayout());
      PainelSuperior.add(chatScroll);
      PainelSuperior.setSize(10, 10);*/
      Painel = new JPanel(new BorderLayout());
      Painel.add(chatScroll, BorderLayout.CENTER);
      ChatJFrame.add(Painel);

      PainelInferior = new JPanel(new BorderLayout());

      CampoChat = new JTextField(20);

      BotaoEnviar = new JButton("Enviar");
      BotaoEnviar.setSize(10,5);
      BotaoEnviar.addActionListener(this);
  

      PainelInferior.add(CampoChat, BorderLayout.CENTER);
    
      AreaDoChat.setFont(new Font("Sans-Serif", Font.ITALIC, 18));
      /*AreaDoChat.setSize(1,1);
      AreaDoChat.setBackground(Color.BLACK);*/
      PainelInferior.add(BotaoEnviar, BorderLayout.EAST);


      Painel.add(PainelInferior, BorderLayout.SOUTH);

     
      CampoChat.addKeyListener(this);

      ChatJFrame.add(Painel);
      ChatJFrame.setVisible(true);
    }

    @Override
  public void actionPerformed(ActionEvent e) {
    try {
      PrintStream saida =  new PrintStream(this.cliente.getOutputStream());
      if(e.getSource()==BotaoEnviar){
        String msg = CampoChat.getText();
        AreaDoChat.append("Você: "+msg+"\n");
        saida.println("Usuário " + this.cliente1.ip + " : "+msg);
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
        saida.println("Usuário " + this.cliente1.ip + " : "+msg);
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
 }

