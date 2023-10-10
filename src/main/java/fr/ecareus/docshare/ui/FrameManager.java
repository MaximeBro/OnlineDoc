package fr.ecareus.docshare.ui;

import fr.ecareus.docshare.ui.panels.MainPage;
import fr.ecareus.docshare.ui.shared.DialogFrame;
import fr.ecareus.docshare.ui.panels.SessionPanel;
import fr.ecareus.docshare.ui.shared.AppBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class FrameManager {

    private final JFrame frame;
    private int xOffSet, yOffSet;
    private int mouseX, mouseY;

    public FrameManager() {
        this.frame = new JFrame();
        this.frame.setUndecorated(true);

        this.frame.setSize(new Dimension(1280, 720));
        this.frame.setJMenuBar(new AppBar(this));

        this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        MainPage page = new MainPage();
        this.frame.add(page);
        page.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        page.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(frame.getX() + e.getX() - mouseX,  frame.getY() + e.getY() - mouseY);
            }
        });

        this.frame.setVisible(true);
    }

    public JFrame getCurrentFrame() {
        return this.frame;
    }

    public void showSessionDialog() {
        DialogFrame dialog = new DialogFrame<SessionPanel>(this.frame, SessionPanel.class);
    }
}
