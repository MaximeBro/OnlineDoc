package fr.ecareus.docshare.ui.shared;

import fr.ecareus.docshare.ui.FrameManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AppBar extends JMenuBar implements ActionListener {

    private final FrameManager manager;
    private final JFrame frame;
    private final List<JMenu> menus;
    private final List<JMenuItem> items;
    public AppBar(FrameManager manager) {
        this.manager = manager;
        this.frame = manager.getCurrentFrame();
        this.menus = new ArrayList<>();
        this.items = new ArrayList<>();

        this.menus.add(new JMenu("Fichier"));
        this.menus.add(new JMenu("Connexion"));
        this.menus.add(new JMenu("Compte"));

        this.items.add(new JMenuItem("Ouvrir")); //0 - Fichier
        this.items.add(new JMenuItem("Nouveau")); //1 - Fichier
        this.items.add(new JMenuItem("Sauvegarder")); //2 - Fichier
        this.items.add(new JMenuItem("Quitter")); //3 - Fichier
        this.items.add(new JMenuItem("Session...")); //4 - Connexion

        this.menus.get(0).add(this.items.get(0));
        this.menus.get(0).add(this.items.get(1));
        this.menus.get(0).add(this.items.get(2));
        this.menus.get(0).add(this.items.get(3));
        this.menus.get(1).add(this.items.get(4));


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
            this.manager.showSessionDialog();

        }
    }
}
