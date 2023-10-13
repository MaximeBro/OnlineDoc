package fr.ecareus.onlinedoc.ui;

import fr.ecareus.onlinedoc.ui.pages.HomePage;
import fr.ecareus.onlinedoc.ui.shared.AppBar;
import fr.ecareus.onlinedoc.ui.shared.IPanel;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanelManager {

    private final Stage stage;
    private GridPane layout;
    private final GridPane contentPane;

    public PanelManager(Stage stage) {
        this.stage = stage;
        contentPane = new GridPane();
    }

    public void init() {
        this.stage.setTitle("OnlineDoc");
        this.stage.setWidth(1280);
        this.stage.setHeight(720);
        this.stage.centerOnScreen();
        this.layout = new GridPane();

        Scene scene = new Scene(this.layout);
        this.stage.setScene(scene);
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.show();

        AppBar appBar = new AppBar();
        RowConstraints topPanelConstraints = new RowConstraints();
        topPanelConstraints.setValignment(VPos.TOP);
        this.layout.getRowConstraints().addAll(topPanelConstraints, new RowConstraints());
        this.layout.add(appBar.getLayout(), 0, 0);
        appBar.init(this);

        this.layout.add(this.contentPane, 0, 1);

        this.layout.setOnMousePressed(press -> {
            this.layout.setOnMouseDragged(drag -> {
                this.stage.setX(drag.getScreenX() - press.getSceneX());
                this.stage.setY(drag.getScreenY() - press.getSceneY());
            });
        });
    }

    public void showPanel(IPanel panel) {
        this.contentPane.getChildren().clear();
        this.contentPane.getChildren().add(panel.getLayout());

        if(panel.getStyleSheetFile() != null) {
            this.stage.getScene().getStylesheets().clear();
            this.stage.getScene().getStylesheets().add(panel.getStyleSheetFile());
        }

        panel.init(this);
        panel.onShow();
    }

    public Stage getStage() { return this.stage; }
}