package fr.ecareus.onlinedoc;

import fr.ecareus.onlinedoc.ui.PanelManager;
import fr.ecareus.onlinedoc.ui.pages.HomePage;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        try {
            launch(Application.class, args);
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void start(Stage stage) throws Exception {
        PanelManager manager = new PanelManager(stage);
        manager.init();
        manager.showPanel(new HomePage());
    }
}
