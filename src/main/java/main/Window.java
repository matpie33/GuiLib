package main;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

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
        FilterCombobox<Integer> combo = new FilterCombobox<>();
        combo.addItem(1555789);
        combo.addItem(789);
        combo.addItem(252525);
        combo.addItem(5252);
        contentPane.add(combo);

        frame.setContentPane(contentPane);
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.pack();
        frame.setVisible(true);
    }



}
