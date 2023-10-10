package fr.ecareus.docshare.ui.shared;

import fr.ecareus.docshare.ui.FrameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class AppBar extends JPanel implements ActionListener {
    private final FrameManager manager;
    private final JFrame frame;
    private final JMenuBar menuBar;
    private final List<JMenu> menus;
    private final List<JMenuItem> items;

    private int mouseX, mouseY;

    public AppBar(FrameManager manager) {
        this.manager = manager;
        this.frame = manager.getCurrentFrame();
        this.menuBar = new JMenuBar();
        this.menus = new ArrayList<>();
        this.items = new ArrayList<>();

        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(this.getWidth(), 25));
        this.setBackground(ThemeHelper.LIGHT_DARK);

        this.menus.add(new JMenu("Fichier"));
        this.menus.add(new JMenu("Connexion"));
        this.menus.add(new JMenu("Compte"));

        this.items.add(new JMenuItem("Ouvrir")); //0 - Fichier
        this.items.add(new JMenuItem("Nouveau")); //1 - Fichier
        this.items.add(new JMenuItem("Sauvegarder")); //2 - Fichier
        this.items.add(new JMenuItem("Quitter")); //3 - Fichier
        this.items.add(new JMenuItem("Sessions")); //4 - Connexion
        this.items.add(new JMenuItem("Configuration")); //5 - Compte

        this.menus.get(0).add(this.items.get(0));
        this.menus.get(0).add(this.items.get(1));
        this.menus.get(0).add(this.items.get(2));
        this.menus.get(0).add(this.items.get(3));
        this.menus.get(1).add(this.items.get(4));
        this.menus.get(2).add(this.items.get(5));

        for(JMenuItem item : this.items) {
            item.setBackground(ThemeHelper.LIGHT_DARK);
            item.setForeground(ThemeHelper.WHITE);
            item.setBorderPainted(false);
            item.addActionListener(this);
        }

        for(JMenu menu : this.menus) {
            menu.setBackground(ThemeHelper.LIGHT_DARK);
            menu.setForeground(ThemeHelper.WHITE);
            menu.setBorderPainted(false);
            this.menuBar.add(menu);
        }

        this.menuBar.setBackground(ThemeHelper.LIGHT_DARK);
        this.menuBar.setBorderPainted(false);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.75;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        this.add(this.menuBar, gridBagConstraints);


        JButton closeBtn = this.unpaintedButton("X");
        JPanel actionPane = new JPanel();
        actionPane.setLayout(new GridLayout(1, 5));
        actionPane.setBackground(ThemeHelper.LIGHT_DARK);
        actionPane.add(this.unpaintedButton(""));
        actionPane.add(this.unpaintedButton(""));
        actionPane.add(this.unpaintedButton(""));
        actionPane.add(this.unpaintedButton("-"));
        actionPane.add(closeBtn);

        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.gridx = 1;
        closeBtn.setForeground(ThemeHelper.WHITE);
        this.add(actionPane, gridBagConstraints);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(frame.getX() + e.getX() - mouseX,  frame.getY() + e.getY() - mouseY);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.items.get(3)) {
            this.frame.dispose();
        }

        if(e.getSource() == this.items.get(4)) {
            this.manager.showSessionDialog();
        }
    }

    public JButton unpaintedButton(String text) {
        JButton btn = new JButton(text);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setForeground(ThemeHelper.WHITE);

        return btn;
    }
}
