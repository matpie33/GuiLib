package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accordion extends JPanel {

    public static final String ACCORDION_IMG_PATH = "/accordion.png";
    private int leftPadding = 40;

    public Accordion (){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    public void addHeader (String header, String content){
        JButton button = createAccordionButton(header);
        Component paddingLeft = Box.createRigidArea(new Dimension(leftPadding, 5));
        JLabel label = new JLabel(content);
        JPanel contentPanel = PanelBuilder.create().setLayout(LayoutType.LINE_BY_LINE).addComponents(
                paddingLeft, label).setVisible(false).build();
        addAccordionButtonAction(button, contentPanel);
        add(button);
        add(contentPanel);
        add(new JSeparator(JSeparator.HORIZONTAL));
    }

    private void addAccordionButtonAction(JButton button, JPanel contentPanel) {
        button.addActionListener(new ActionListener() {
            boolean isPanelVisible = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                isPanelVisible = !isPanelVisible;
                contentPanel.setVisible(isPanelVisible);
            }
        });
    }

    private JButton createAccordionButton(String header) {
        ImageIcon defaultIcon = new ImageIcon(getClass().getResource(ACCORDION_IMG_PATH));
        return ButtonBuilder.create(header).removeBorder()
                        .setFontSize(16f).setTransparent().setIcon(defaultIcon).build();
    }

}
