package fr.ecareus.onlinedoc.ui.shared;

import com.sun.prism.paint.Paint;
import fr.ecareus.onlinedoc.ui.PanelManager;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.swing.border.LineBorder;

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

        Button closeBtn = new Button();
        closeBtn.setBackground(null);
        FontIcon icon = new FontIcon("bi-x");
        icon.setIconSize(32);
        icon.setIconColor(Color.WHITE);
        closeBtn.setGraphic(icon);
        closeBtn.setAlignment(Pos.BASELINE_RIGHT);
        closeBtn.setOnMouseClicked(event -> System.exit(0));
        closeBtn.setOnMouseEntered(event -> icon.setIconColor(Color.GRAY));
        closeBtn.setOnMouseExited(event -> icon.setIconColor(Color.WHITE));

        actionPane.getChildren().add(closeBtn);
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
        sessionMenu.getItems().addAll(browseSession, refreshSession);

        Menu accountMenu = new Menu("Compte");

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
}
