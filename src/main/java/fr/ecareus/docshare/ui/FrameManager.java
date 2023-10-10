package fr.ecareus.docshare.ui;

import fr.ecareus.docshare.ui.panels.MainPage;
import fr.ecareus.docshare.ui.shared.DialogFrame;
import fr.ecareus.docshare.ui.panels.SessionPanel;
import fr.ecareus.docshare.ui.shared.ICentered;
import fr.ecareus.docshare.ui.shared.AppBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class FrameManager implements ICentered {

    private final JFrame frame;
    private int xOffSet, yOffSet;
    private int mouseX, mouseY;

    public FrameManager() {
        // Used to set the whole application's appearance to the user's system's look and feel
        // Using this method will break a lot of style such as menu colors...
        // try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) { e.printStackTrace(); }

        this.frame = new JFrame();
        this.frame.setLayout(new BorderLayout());
        this.frame.setUndecorated(true);

        this.frame.setSize(new Dimension(1280, 720));
        this.frame.setLocation(this.getCenterLocation(1280, 720));


        this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        MainPage page = new MainPage();
        this.frame.add(new AppBar(this), BorderLayout.NORTH);
        this.frame.add(page, BorderLayout.CENTER);
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
        DialogFrame dialog = new DialogFrame<SessionPanel>(frame, SessionPanel.class, true);
    }
}
