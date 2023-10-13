package fr.ecareus.onlinedoc.ui.shared;

import fr.ecareus.onlinedoc.ui.PanelManager;
import javafx.scene.layout.GridPane;

public interface IPanel {

    void init(PanelManager manager);
    GridPane getLayout();

    String getStyleSheetFile();

    String getName();

    void onShow();
}
