package fr.ecareus.docshare.ui.panels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SessionPanel extends JPanel implements ActionListener {
    private JFrame parent;
    private JButton cancelButton;
    private JButton validateButton;

    public SessionPanel() {
        this.setLayout(new BorderLayout());
        JPanel subPane = new JPanel();
        this.cancelButton = new JButton("Annuler");
        this.validateButton = new JButton("Valider");

        subPane.add(this.cancelButton);
        subPane.add(this.validateButton);
        this.add(subPane, BorderLayout.SOUTH);

        this.cancelButton.addActionListener(this);
        this.validateButton.addActionListener(this);

        this.setBorder(new LineBorder(Color.BLACK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.cancelButton) {
            if(this.parent != null) {
                this.parent.dispose();
            }
            return;
        }

        if(e.getSource() == this.validateButton) {
            if(this.parent != null) {
                this.parent.dispose();
            }
            return;
        }
    }

    public void setParent(JFrame parent) { this.parent = parent; }
}
