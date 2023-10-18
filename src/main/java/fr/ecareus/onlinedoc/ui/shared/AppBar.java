package fr.ecareus.onlinedoc.ui.shared;

import fr.ecareus.onlinedoc.models.User;
import fr.ecareus.onlinedoc.ui.PanelManager;
import fr.ecareus.onlinedoc.ui.pages.SessionsPage;
import fr.ecareus.onlinedoc.ui.pages.UserAccountPage;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.kordamp.ikonli.javafx.FontIcon;

public class AppBar extends Panel {

    @Override
    public void init(PanelManager manager) {
        super.init(manager);
        this.layout.setStyle("-fx-background-color: rgb(35, 40, 40);");
        setCanTakeAllWidth(this.layout);



        // AppBar : TopPane => Title - Action buttons
        GridPane topPane = new GridPane();
        GridPane titlePane = new GridPane();
        GridPane actionPane = new GridPane();
        Label titleLbl = new Label("OnlineDoc");
        titleLbl.setFont(Font.font("Consolas", FontWeight.NORMAL, FontPosture.REGULAR, 18f));
        titleLbl.setStyle("-fx-text-fill: white;");
        setLeft(titleLbl);
        titleLbl.setTranslateX(15d);
        titlePane.setTranslateY(10d);
        titlePane.getChildren().add(titleLbl);

        Button minimizeBtn = new Button();
        minimizeBtn.setBackground(null);
        FontIcon miniIcon = new FontIcon("bi-dash");
        miniIcon.setIconSize(32);
        miniIcon.setIconColor(Color.WHITE);
        minimizeBtn.setGraphic(miniIcon);
        minimizeBtn.setAlignment(Pos.BASELINE_RIGHT);
        minimizeBtn.setTranslateX(-45);
        minimizeBtn.setOnMouseClicked(event -> this.manager.getStage().setIconified(true));
        minimizeBtn.setOnMouseEntered(event -> miniIcon.setIconColor(Color.GRAY));
        minimizeBtn.setOnMouseExited(event -> miniIcon.setIconColor(Color.WHITE));

        Button closeBtn = new Button();
        closeBtn.setBackground(null);
        FontIcon closeIcon = new FontIcon("bi-x");
        closeIcon.setIconSize(32);
        closeIcon.setIconColor(Color.WHITE);
        closeBtn.setGraphic(closeIcon);
        closeBtn.setAlignment(Pos.BASELINE_RIGHT);
        closeBtn.setOnMouseClicked(event -> System.exit(0));
        closeBtn.setOnMouseEntered(event -> closeIcon.setIconColor(Color.GRAY));
        closeBtn.setOnMouseExited(event -> closeIcon.setIconColor(Color.WHITE));

        actionPane.getChildren().addAll(minimizeBtn, closeBtn);
        setCanTakeAllSize(titlePane);
        topPane.add(titlePane, 0, 0);
        topPane.add(actionPane, 1, 0);
        this.layout.getChildren().add(topPane);


        // AppBar : Menus
        GridPane menuPane = new GridPane();
        menuPane.setStyle(  "-fx-background-color: rgb(35, 40, 40);" +
                            "-fx-border-width: 1 0 0 0;" +
                            "-fx-border-style: solid;" +
                            "-fx-border-color: gray;");


        Menu fileMenu = new Menu("Fichier");
        MenuItem openFile = new MenuItem("Ouvrir");
        MenuItem newFile = new MenuItem("Nouveau");
        MenuItem saveFile = new MenuItem("Enregistrer");
        MenuItem closeFile = new MenuItem("Fermer");
        fileMenu.getItems().addAll(openFile, newFile, saveFile, closeFile);

        Menu sessionMenu = new Menu("Connexion");
        MenuItem browseSession = new MenuItem("Sessions...");
        MenuItem refreshSession = new MenuItem("Relancer la session actuelle");
        browseSession.setOnAction(e -> {
            SessionsPage page = new SessionsPage();
            page.init(this.manager);
        });
        sessionMenu.getItems().addAll(browseSession, refreshSession);

        Menu accountMenu = new Menu("Compte");
        MenuItem configAccount = new MenuItem("Configuration");
        configAccount.setOnAction(e ->
        {
            UserAccountPage page = new UserAccountPage();
            page.init(this.manager);
        });
        accountMenu.getItems().addAll(configAccount);

        MenuBar menuBar = new MenuBar();
        menuBar.setTranslateY(1d);
        menuBar.getMenus().addAll(fileMenu, sessionMenu, accountMenu);
        menuPane.getChildren().addAll(menuBar);

        setCanTakeAllWidth(menuPane);
        this.layout.add(menuPane, 0, 1);
    }

    @Override
    public String getName() {
        return "AppBar";
    }

    public double getHeight() { return this.layout.getPrefHeight(); }
}
