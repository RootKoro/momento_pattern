package pattern_package;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
// import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Main extends JFrame {
    public static void main(String[] args) {
        new Main();
    }

    private JButton saveBtn, undoBtn, redoBtn;
    private JTextArea textArea = new JTextArea(40, 60);

    Saver saver = new Saver();
    Generator generator = new Generator();
    int saveFiles = 0 , currentText = 0;

    public Main(){
        this.setSize(750, 780);
        this.setTitle("Memento Design pattern");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.add(textArea);

        ButtonListener saveListener = new ButtonListener();
        ButtonListener undoListener = new ButtonListener();
        ButtonListener redoListener = new ButtonListener();

        saveBtn = new JButton("Save");
        saveBtn.addActionListener(saveListener);

        undoBtn = new JButton("Undo");
        undoBtn.addActionListener(undoListener);
        
        redoBtn = new JButton("Redo");
        redoBtn.addActionListener(redoListener);

        panel.add(saveBtn);
        panel.add(undoBtn);
        panel.add(redoBtn);

        this.add(panel);
        this.setVisible(true);
        redoBtn.setEnabled(false);
        undoBtn.setEnabled(false);
    }
    
    class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == saveBtn){
                String textInArea = textArea.getText();
                generator.setText(textInArea);
                saver.addMemento(generator.saveInMemento());

                saveFiles++;
                currentText++;

                System.out.println("Save files : " + saveFiles);
                undoBtn.setEnabled(true);
            }else if(e.getSource() == undoBtn){
                if(currentText > 0){
                    currentText--;
                    String textBoxString = generator.restoreFromMemento(saver.getMemento(currentText));
                    textArea.setText(textBoxString);
                    redoBtn.setEnabled(true);
                } else  undoBtn.setEnabled(false);
            }else if(e.getSource() == redoBtn){
                if ((saveFiles - 1) > currentText){
                    currentText++;
                    String textBoxString = generator.restoreFromMemento(saver.getMemento(currentText));
                    textArea.setText(textBoxString);
                    undoBtn.setEnabled(true);
                    }else redoBtn.setEnabled(false);
            }
        }
    }
}
