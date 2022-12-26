package WirelessCalculatorGUI;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JTextFieldNumListener implements KeyListener {

    JTextField textField;

    public JTextFieldNumListener(JTextField textField){
        this.textField=textField;
    }

    @Override
    public void keyTyped(KeyEvent e) {
       if((e.getKeyChar()<'0' || e.getKeyChar()>'9') && e.getKeyChar()!='.' && e.getKeyChar()!='-'){
            e.consume();
        }
        else if(textField.getText().contains(".") && e.getKeyChar() =='.'){
            e.consume();
        }
        else if(e.getKeyChar() =='-' && !textField.getText().isEmpty()){
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
