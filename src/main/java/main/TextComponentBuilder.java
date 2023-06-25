package main;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class TextComponentBuilder {

    private JTextComponent textField;

    private TextComponentBuilder(TextComponentType textComponentType){
        if (textComponentType.equals(TextComponentType.TEXT_FIELD)){
            textField = new JTextField();
            textField.setAlignmentX(Component.LEFT_ALIGNMENT);

        }

    }

    public static TextComponentBuilder create (TextComponentType textComponentType){
        return new TextComponentBuilder(textComponentType);
    }

    public TextComponentBuilder setText (String text){
        textField.setText(text);
        return this;
    }
    public TextComponentBuilder setForeground (Color color){
        textField.setForeground(color);
        return this;
    }

    public TextComponentBuilder setEditable (boolean editable){
        textField.setEditable(editable);
        return this;
    }

    public JTextComponent build (){
        return textField;
    }


}
