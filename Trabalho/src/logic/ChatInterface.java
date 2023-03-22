package logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.*;

public class ChatInterface extends JFrame implements ActionListener {

    private JTextArea AreaDoChat;
    private JTextField CampoChat;
    private JButton BotaoEnviar;

    public ChatInterface() {
        super("Chat Interface");
        //Define tamanho do campo do chat e define modo de fechamento
        setSize(600, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criando e customizando área de chat
        AreaDoChat = new JTextArea();
        AreaDoChat.setEditable(false);
        AreaDoChat.setBackground(Color.BLACK);
        AreaDoChat.setForeground(Color.WHITE);
        JScrollPane chatScroll = new JScrollPane(AreaDoChat);
        chatScroll.setPreferredSize(new Dimension(350, 250));
        AreaDoChat.setFont( new Font("Serif", Font.BOLD, 20) );
        

        // Criando campo de entrada de texto
        CampoChat = new JTextField();
        CampoChat.addActionListener(this);

        // Criando botão de envio
        BotaoEnviar = new JButton("Enviar");
        BotaoEnviar.addActionListener(this);

        // Adicionando componentes na janela
        JPanel chat = new JPanel(new BorderLayout());
        chat.add(chatScroll, BorderLayout.CENTER);

        JPanel PainelInferior = new JPanel(new BorderLayout());
        PainelInferior.add(CampoChat, BorderLayout.CENTER);
        PainelInferior.add(BotaoEnviar, BorderLayout.EAST);

        chat.add(PainelInferior, BorderLayout.SOUTH);

        getContentPane().add(chat);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BotaoEnviar) {
            String text = CampoChat.getText();
            CampoChat.setText("");
            AreaDoChat.append(text + "\n");
        }
    }

}