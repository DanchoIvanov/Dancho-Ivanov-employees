package ui;

import core.Application;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class MainUI {
    private JPanel rootPanel;
    private JButton selectFileButton;
    private JButton runButton;
    private JTextField filePathTextField;
    private JTextField resultTextField;
    private JTextArea errorLog;

    public MainUI() {
        runButton.addActionListener(e -> this.run());
        selectFileButton.addActionListener(e -> this.selectFile());
        resultTextField.setEditable(false);
        errorLog.setEditable(false);

    }
    private void selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".csv");
                }
            }

            @Override
            public String getDescription() {
                return "Comma Separated Values";
            }
        });
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(selectFileButton);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.filePathTextField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void run() {
        Application application = new Application(filePathTextField.getText());
        resultTextField.setText(application.solve());
        errorLog.setText(application.getErrorLog());
    }

    public JPanel getRootPanel() {
        return this.rootPanel;
    }
}
