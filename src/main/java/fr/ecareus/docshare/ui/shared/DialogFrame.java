package fr.ecareus.docshare.ui.shared;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DialogFrame<T> {
    private final JFrame frame;
    private final JFrame parent;
    private T panel;
    private int mouseX, mouseY;

    // Fully dynamic class that acts as a Dialog
    public DialogFrame(JFrame parent, Class panel) {
        this.parent = parent;
        this.frame = new JFrame();
        this.frame.setUndecorated(true);
        this.frame.setSize(new Dimension(720, 480));
        try {
            Class<?> panelClass = Class.forName(panel.getName());
            Method setParent = panelClass.getMethod("setParent", JFrame.class);
            this.panel = (T) panelClass.getDeclaredConstructor().newInstance();
            setParent.invoke(this.panel, this.frame);

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
                    frame.setLocation(frame.getX() + e.getX() - mouseX,  frame.getY() + e.getY() - mouseY);
                }
            });

            this.frame.add((JPanel)this.panel);
        } catch (NullPointerException | NoSuchMethodException | InvocationTargetException | InstantiationException | ClassNotFoundException | IllegalAccessException e) { e.printStackTrace(); }

        this.frame.setVisible(true);
    }
}
