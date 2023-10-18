package fr.ecareus.onlinedoc.ui.shared;

import fr.ecareus.onlinedoc.models.User;
import fr.ecareus.onlinedoc.ui.PanelManager;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class Dialog extends Panel {

    protected Stage stage;
    protected User currentUser;

    protected Label title;

    public Dialog() {
        this.stage = new Stage();
        this.layout.setOnMousePressed(press -> {
            this.layout.setOnMouseDragged(drag -> {
                this.stage.setX(drag.getScreenX() - press.getSceneX());
                this.stage.setY(drag.getScreenY() - press.getSceneY());
            });
        });
    }

    @Override
    public void init(PanelManager manager) {
        super.init(manager);
        this.currentUser = this.manager.getUser();

        this.layout.setPrefSize(720, 480);
        this.layout.getRowConstraints().addAll(new RowConstraints(35), new RowConstraints(395), new RowConstraints(50));
        this.layout.setStyle("-fx-background-color: rgb(35, 40, 40);" +
                             "-fx-border-width: 1;" +
                             "-fx-border-style: solid;" +
                             "-fx-border-color: white;");

        this.stage.centerOnScreen();
        this.stage.initOwner(this.manager.getStage());
        this.stage.setScene(new Scene(this.layout));
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.toFront();
        this.stage.show();

        GridPane titlePane = new GridPane();
        titlePane.setStyle( "-fx-background-color: rgb(35, 40, 40);" +
                "-fx-border-width: 0 0 1 0;" +
                "-fx-border-style: solid;" +
                "-fx-border-color: gray;");
        setCanTakeAllSize(titlePane);

        this.title = new Label("Dialog");
        this.title.setFont(Font.font("Consolas", FontWeight.LIGHT, FontPosture.REGULAR, 16f));
        this.title.setStyle("-fx-text-fill: gray;");
        setCanTakeAllSize(this.title);
        setCenterV(this.title);
        setCenterH(this.title);
        this.title.setTranslateX(15);
        titlePane.getChildren().add(this.title);

        this.layout.add(titlePane, 0, 0);
    }

    public void setTitle(String title) { this.title.setText(title); }
}
