package main;

import org.springframework.stereotype.Component;
import org.w3c.dom.Text;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

@Component
public class Window {

    @PostConstruct
    public void init (){
        JFrame frame = new JFrame();
        JPanel contentPane = new JPanel();
        JButton jButton = new JButton();
        contentPane.add(new JLabel("title"));
        contentPane.add(new JButton("Start"));
        JTextComponent helloWorld = TextComponentBuilder.create(TextComponentType.TEXT_FIELD).setText("Hello world").setForeground(Color.red).setEditable(false).build();
        contentPane.add(helloWorld);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }


}
