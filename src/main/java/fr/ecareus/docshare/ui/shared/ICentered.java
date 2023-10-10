package fr.ecareus.docshare.ui.shared;

import java.awt.*;

public interface ICentered {

    default Point getCenterLocation(int width, int height) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2);
    }
}
