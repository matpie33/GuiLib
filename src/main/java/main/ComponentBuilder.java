package main;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

@SuppressWarnings("unchecked")
public class ComponentBuilder<T extends ComponentBuilder<T, C>, C extends JComponent> {

    protected C component;

    protected ComponentBuilder(){
    }

    public T removeBorder (){
        component.setBorder(null);
        return (T)this;
    }
    public T setForeground (Color color){
        component.setForeground(color);
        return (T)this;
    }

    public T setFontSize (float size){
        component.setFont(component.getFont().deriveFont(size));
        return (T)this;
    }

    public T setVisible (boolean visible){
        component.setVisible(visible);
        return (T) this;
    }

    public C build (){
        return component;
    }


}
