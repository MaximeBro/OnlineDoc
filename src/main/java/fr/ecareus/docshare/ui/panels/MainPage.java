package fr.ecareus.docshare.ui.panels;

import fr.ecareus.docshare.ui.shared.ThemeHelper;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainPage extends JPanel {

    private JTextArea textArea;

    public MainPage() {
        this.setBackground(ThemeHelper.TEXT_PRIMARY);
        this.setLayout(new BorderLayout());

        this.textArea = new JTextArea();
        this.textArea.setBackground(Color.LIGHT_GRAY);
        this.textArea.setBorder(new LineBorder(Color.DARK_GRAY));

        JScrollPane scrollPane = new JScrollPane(this.textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel leftPane = new JPanel();
        leftPane.setPreferredSize(new Dimension(120, 0));
        leftPane.setBackground(Color.GRAY);

        JPanel rightPane = new JPanel();
        rightPane.setPreferredSize(new Dimension(120, 0));
        rightPane.setBackground(Color.GRAY);
        this.add(leftPane, BorderLayout.WEST);
        this.add(rightPane, BorderLayout.EAST);
        this.add(scrollPane, BorderLayout.CENTER);
    }
}
