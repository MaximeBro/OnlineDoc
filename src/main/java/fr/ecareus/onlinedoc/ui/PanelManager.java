package fr.ecareus.onlinedoc.ui;

import fr.ecareus.onlinedoc.models.User;
import fr.ecareus.onlinedoc.ui.shared.AppBar;
import fr.ecareus.onlinedoc.ui.shared.IPanel;
import fr.ecareus.onlinedoc.ui.shared.Panel;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.*;

public class PanelManager {

    private final Stage stage;
    private final String path;
    private GridPane layout;
    private final GridPane contentPane;
    private Panel currentPanel;
    private final User user;

    public PanelManager(Stage stage, String appPath) {
        this.stage = stage;
        this.path = appPath;
        this.user = new User(appPath + "\\Documents");
        contentPane = new GridPane();
    }

    public void init() {
        this.stage.setTitle("OnlineDoc");
        this.stage.setWidth(1280);
        this.stage.setHeight(720);
        this.stage.centerOnScreen();
        this.layout = new GridPane();
        this.layout.setStyle("-fx-background-color: rgb(35, 40, 40);");

        Scene scene = new Scene(this.layout);
        this.stage.setScene(scene);
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.show();

        AppBar appBar = new AppBar();
        RowConstraints topPanelConstraints = new RowConstraints(appBar.getHeight());
        topPanelConstraints.setValignment(VPos.TOP);
        this.layout.getRowConstraints().addAll(topPanelConstraints, new RowConstraints());
        this.layout.add(appBar.getLayout(), 0, 0);
        appBar.init(this);

        GridPane.setHgrow(this.contentPane, Priority.ALWAYS);
        GridPane.setVgrow(this.contentPane, Priority.ALWAYS);
        this.layout.add(this.contentPane, 0, 1);

        this.layout.setOnMousePressed(press -> {
            this.layout.setOnMouseDragged(drag -> {
                this.stage.setX(drag.getScreenX() - press.getSceneX());
                this.stage.setY(drag.getScreenY() - press.getSceneY());
            });
        });

        this.stage.getIcons().add(new Image("images/icon.png"));
        this.initConfig();
    }

    public void showPanel(Panel panel) {
        this.contentPane.getChildren().clear();
        this.contentPane.getChildren().add(panel.getLayout());
        this.currentPanel = panel;

        if(panel.getStyleSheetFile() != null) {
            this.stage.getScene().getStylesheets().clear();
            this.stage.getScene().getStylesheets().add(panel.getStyleSheetFile());
        }

        panel.init(this);
        panel.onShow();
    }

    public Stage getStage() { return this.stage; }

    public User getUser() { return user; }

    private void initConfig() {
        File configFolder = new File(this.path);
        File docsFolder = new File(this.path + "\\Documents");
        File configFile = new File(this.path + "\\config.cfg");

        if(!configFolder.exists())
            configFolder.mkdir();

        if(!docsFolder.exists())
            docsFolder.mkdir();

        if(!configFile.exists()) {
            try {
                configFile.createNewFile();
                Writer writer = new FileWriter(configFile, false);
                String content = "# Configuration principale, ne pas modifier à moins que vous ne soyez certain de ce que vous faites.\n";

                content += "\nuser=" + this.user.getName() + "\n";
                content += "\ndocuments= [\n";
                for(File document : new File(this.user.getDocumentsPath()).listFiles())
                    content += " - " + "\"" + document.getName() + "\"\n";

                content += "]\n";
                content += "";

                writer.write(content);
                writer.flush();
                writer.close();
            } catch (IOException e) { e.printStackTrace(); }
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(configFile));
                String line = reader.readLine();
                while(line != null && line.length() > 0) {
                    if(line.charAt(0) != '#' && line.contains("user=")) {
                        this.user.setName(line.replace("user=", ""));
                    }

                    line = reader.readLine();
                }

                reader.close();
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    public void saveConfig() {



    }

    public void refresh() {
        this.currentPanel.refresh();
    }
}