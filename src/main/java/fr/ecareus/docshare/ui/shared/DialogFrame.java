package fr.ecareus.docshare.ui.shared;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;

public class DialogFrame<T> extends JDialog implements ICentered {
    private final JFrame parent;
    private T panel;
    private int mouseX, mouseY;

    // Fully dynamic class that acts as a Dialog
    public DialogFrame(JFrame parent, Class panel, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        this.setUndecorated(true);
        this.setSize(new Dimension(720, 480));
        this.setLocation(this.getCenterLocation(720, 480));
        try {
            Class<?> panelClass = Class.forName(panel.getName());
            Method setParent = panelClass.getMethod("setParent", JDialog.class);
            this.panel = (T) panelClass.getDeclaredConstructor().newInstance();
            setParent.invoke(this.panel, this);

            ((JPanel)this.panel).addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    mouseX = e.getX();
                    mouseY = e.getY();
                }
            });

            ((JPanel)this.panel).addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    DialogFrame.this.setLocation(DialogFrame.this.getX() + e.getX() - mouseX,  DialogFrame.this.getY() + e.getY() - mouseY);
                }
            });

            this.add((Component) this.panel);
        } catch (Exception e) { e.printStackTrace(); }

        this.setVisible(true);
    }
}
