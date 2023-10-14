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
        String osName = System.getProperty("os.name");
        String userName = System.getProperty("user.name");
        String configPath = osName.contains("Windows") ? "C:\\Users\\" + userName + "\\AppData\\Roaming\\OnlineDoc" : "/home/onlinedoc";

        PanelManager manager = new PanelManager(stage, configPath);
        manager.init();
        manager.showPanel(new HomePage());
    }
}
