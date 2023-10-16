package fr.ecareus.onlinedoc.ui;

import fr.ecareus.onlinedoc.models.*;
import fr.ecareus.onlinedoc.network.SessionManager;
import fr.ecareus.onlinedoc.ui.pages.SessionsPage;
import fr.ecareus.onlinedoc.ui.shared.AppBar;
import fr.ecareus.onlinedoc.ui.shared.Panel;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PanelManager {

    private final Stage stage;
    private final String path;
    private GridPane layout;
    private final GridPane contentPane;
    private Panel currentPanel;
    private User user;
    private final SessionManager sessionManager;
    private final List<AvailableSession> sessionList;
    private Config config;
    private Document currentDocument;

    public PanelManager(Stage stage, String appPath) throws IOException {
        this.sessionManager = new SessionManager(this);
        this.stage = stage;
        this.path = appPath;
        contentPane = new GridPane();
        this.sessionList = new ArrayList<>();
        this.config = new Config();
    }

    public void init() {
        this.config.init(this.path);
        this.user = this.config.getUser();
        this.sessionManager.init();

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
    public void addSession(AvailableSession session) {
        this.sessionList.add(session);
        if(this.currentPanel.getType() instanceof SessionsPage) {
            this.refresh();
        }
    }

    public List<AvailableSession> getSessions() { return this.sessionList; }

    public Document getCurrentDocument() { return this.currentDocument; }

    public void saveConfig() {
        this.config.save(this.path);
    }

    public void refresh() {
        this.currentPanel.refresh();
    }
}