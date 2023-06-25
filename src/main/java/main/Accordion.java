package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Accordion extends JPanel {

    public static final String ACCORDION_CLOSED_PATH = "/accordion_closed.png";
    public static final String ACCORDION_OPENED_PATH = "/accordion_opened.png";
    private final int leftPadding = 40;
    private final ImageIcon closedAccordionIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(ACCORDION_CLOSED_PATH)));
    private final ImageIcon openedAccordionIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(ACCORDION_OPENED_PATH)));

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
            boolean isAccordionOpened = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                isAccordionOpened = !isAccordionOpened;
                 button.setIcon(isAccordionOpened ? openedAccordionIcon : closedAccordionIcon);
                contentPanel.setVisible(isAccordionOpened);
            }
        });
    }

    private JButton createAccordionButton(String header) {
        return ButtonBuilder.create(header).removeBorder()
                        .setFontSize(16f).setTransparent().setIcon(closedAccordionIcon).build();
    }

}
