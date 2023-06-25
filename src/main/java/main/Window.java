package main;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        Accordion tree = new Accordion();
        tree.addHeader("Test header", "Lorem ipsum");
        tree.addHeader("other header", "other lorem ipsum");
        contentPane.add(tree);

        frame.setContentPane(contentPane);
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.pack();
        frame.setVisible(true);
    }


}
