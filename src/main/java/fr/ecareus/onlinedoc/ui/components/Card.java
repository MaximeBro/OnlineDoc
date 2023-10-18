package fr.ecareus.onlinedoc.ui.components;

import fr.ecareus.onlinedoc.models.AvailableSession;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.UUID;

public class Card {

    private final UUID id;
    private final String title;
    private final int users;
    private final GridPane layout;
    private final AvailableSession session;
    private final GridPane contentPane;
    public Card(AvailableSession session) {
        this.session = session;
        this.contentPane = new GridPane();
        this.id = session.getDocumentId();
        this.title = session.getTitle();
        this.users = session.getUsers();
        this.layout = new GridPane();
        this.init();
    }

    public void init() {
        GridPane.setHgrow(this.layout, Priority.ALWAYS);
        GridPane.setVgrow(this.layout, Priority.ALWAYS);
        this.layout.getRowConstraints().addAll(new RowConstraints(), new RowConstraints(40));
        this.layout.setMinSize(200, 200);
        this.layout.setMaxSize(200, 240);
        this.layout.setStyle("-fx-background-color: rgb(255, 255, 255);" +
                             "-fx-background-radius: 8px;");

        this.contentPane.getStyleClass().add("content");

        GridPane titlePane = new GridPane();
        titlePane.setStyle( "-fx-border-width: 1 0 0 0;" +
                            "-fx-border-style: solid;" +
                            "-fx-border-color: gray;");
        Label titleLbl = new Label(title);
        titleLbl.setFont(Font.font("Consolas", FontWeight.NORMAL, FontPosture.REGULAR, 14f));
        GridPane.setHalignment(titleLbl, HPos.LEFT);

        Label usersLbl = new Label("Connectés : " + users);
        usersLbl.setFont(Font.font("Consolas", FontWeight.NORMAL, FontPosture.REGULAR, 14f));
        GridPane.setHalignment(usersLbl, HPos.LEFT);

        titleLbl.setTranslateX(5d);
        usersLbl.setTranslateX(5d);
        titlePane.add(titleLbl, 0, 0);
        titlePane.add(usersLbl, 0, 1);

        GridPane.setHgrow(contentPane, Priority.ALWAYS);
        GridPane.setVgrow(contentPane, Priority.ALWAYS);

        GridPane.setHgrow(titlePane, Priority.ALWAYS);
        GridPane.setVgrow(titlePane, Priority.ALWAYS);

        this.layout.add(this.contentPane, 0, 0);
        this.layout.add(titlePane, 0, 1);
    }

    public GridPane getLayout() { return this.layout; }
    public AvailableSession getSession() { return this.session; }
    public void setSelected(boolean focus) {
        this.layout.getStyleClass().clear();
        this.contentPane.getStyleClass().clear();
        this.contentPane.getStyleClass().add("content");
        if(focus)
            this.layout.getStyleClass().add("content-selected");
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public int getUsers() { return users; }
}
