package fr.ecareus.docshare.ui.shared;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AppBar extends JMenuBar implements ActionListener {

    private final JFrame frame;
    private final List<JMenu> menus;
    private final List<JMenuItem> items;
    public AppBar(JFrame frame) {
        this.frame = frame;
        this.menus = new ArrayList<>();
        this.items = new ArrayList<>();



        this.items.add(new JMenuItem("Ouvrir"));
        this.items.add(new JMenuItem("Nouveau"));
        this.items.add(new JMenuItem("Sauvegarder"));
        this.items.add(new JMenuItem("Quitter"));

        this.menus.add(new JMenu("Fichier"));
        this.menus.add(new JMenu("Connexion"));
        this.menus.add(new JMenu("Compte"));

        for(JMenuItem item : this.items)
            item.addActionListener(this);


        for(JMenu menu : this.menus)
            this.add(menu);

        this.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.items.get(3)) {
            this.frame.dispose();
        }

        if(e.getSource() == this.items.get(4)) {

        }
    }
}
