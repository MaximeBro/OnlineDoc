package fr.ecareus.docshare.ui.panels;

import fr.ecareus.docshare.ui.shared.ThemeHelper;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SessionPanel extends JPanel implements ActionListener {
    private JDialog parent;
    private JButton cancelButton;
    private JButton validateButton;

    public SessionPanel() {
        this.setLayout(new BorderLayout());

        // Buttons
        this.cancelButton = new JButton("Annuler");
        this.validateButton = new JButton("Valider");

        this.cancelButton.setBackground(ThemeHelper.BLACK);
        this.cancelButton.setBorderPainted(false);
        this.cancelButton.setFocusPainted(false);
        this.cancelButton.setForeground(ThemeHelper.WHITE);

        this.validateButton.setBackground(ThemeHelper.SUCCESS);
        this.validateButton.setBorderPainted(false);
        this.validateButton.setFocusPainted(false);
        this.validateButton.setForeground(ThemeHelper.WHITE);



        // Panels
        JPanel contentPane = new JPanel();
        JPanel actionPane = new JPanel();

        contentPane.setBackground(ThemeHelper.DARK);

        actionPane.setLayout(null);
        actionPane.setPreferredSize(new Dimension(this.getWidth(), 50));
        actionPane.setBackground(ThemeHelper.LIGHT_DARK);
        actionPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, ThemeHelper.TEXT_PRIMARY));

        actionPane.add(this.cancelButton);
        actionPane.add(this.validateButton);

        this.cancelButton.setBounds(580 - 120, (int) (50 / 2 - 12.5), 100, 25);
        this.validateButton.setBounds(580, (int) (50 / 2 - 12.5), 100, 25);
        this.cancelButton.addActionListener(this);
        this.validateButton.addActionListener(this);

        this.add(contentPane, BorderLayout.CENTER);
        this.add(actionPane, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.cancelButton) {
            if(this.parent != null) {
                // TO-DO : Load selected Document via connecting to the session
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

    public void setParent(JDialog parent) { this.parent = parent; }
}
