package main;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FilterCombobox<T> extends JComboBox<T> {

    private List<T> items = new ArrayList<>();


    @Override
    public void addItem(T item) {
        items.add(item);
        super.addItem(item);
    }

    @Override
    public void removeItem(Object anObject) {
        items.remove(anObject);
        super.removeItem(anObject);
    }

    public FilterCombobox (){
        setEditable(true);
        getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                JTextField source = (JTextField) e.getSource();
                String previousText = source.getText();
                String newText = source.getText();

                if (e.getKeyChar() == KeyEvent.VK_ENTER){
                    hidePopup();
                    return;
                }
                String selectedText = source.getSelectedText();
                if (isDigitOrLetter(e)){
                    if (selectedText != null){
                        newText = newText.replace(selectedText,""+ e.getKeyChar());
                    }
                    else{
                        newText += e.getKeyChar();

                    }
                }
                else if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE){
                    return;
                }
                List<T> filtered = filterItems(newText);
                DefaultComboBoxModel<T> model = (DefaultComboBoxModel<T>) dataModel;
                model.removeAllElements();
                if (!filtered.isEmpty()){
                    model.addAll(filtered);
                    if (selectedText != null){
                        source.setText(previousText.replace(selectedText, ""));
                    }
                    else{

                        source.setText(previousText);
                    }


                }
                else{
                    source.setText(previousText);
                }

                hidePopup();
                showPopup();

            }
        });
    }

    private boolean isDigitOrLetter(KeyEvent e) {
        return Character.isAlphabetic(e.getKeyChar()) || Character.isDigit(e.getKeyChar());
    }

    private List<T> filterItems(String text) {
        List<T> filtered = new ArrayList<>();
        for (T item : items) {
            if (item.toString().toLowerCase().contains(text.toLowerCase())) {
                filtered.add(item);
            }
        }
        return filtered;
    }

}
