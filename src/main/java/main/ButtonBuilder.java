package main;

import javax.swing.*;
import java.awt.*;

public class ButtonBuilder extends ComponentBuilder<ButtonBuilder, JButton> {


    private ButtonBuilder(String text){
        component = new JButton(text);
        component.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public ButtonBuilder setTransparent (){
        JButton button = component;
        button.setContentAreaFilled(false);
        return this;
    }
    public ButtonBuilder setIcon (Icon icon){
        JButton button = component;
        button.setIcon(icon);
        return this;
    }

    public static ButtonBuilder create (String text){
        return new ButtonBuilder(text);
    }


}
