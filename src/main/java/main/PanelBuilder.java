package main;

import javax.swing.*;
import java.awt.*;

public class PanelBuilder extends ComponentBuilder<PanelBuilder, JPanel> {


    private PanelBuilder(){
        component = new JPanel();
        component.setAlignmentX(Component.LEFT_ALIGNMENT);

    }

    public PanelBuilder setLayout (LayoutType layoutType){
        if (layoutType.equals(LayoutType.LINE_BY_LINE)){
            component.setLayout(new BoxLayout(component, BoxLayout.PAGE_AXIS));
        }
        return this;
    }
    public PanelBuilder addComponents (Component... components){
        for (Component component : components) {
            this.component.add(component);
        }
        return this;
    }



    public static PanelBuilder create (){
        return new PanelBuilder();
    }


}
