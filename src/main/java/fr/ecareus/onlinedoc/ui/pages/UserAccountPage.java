package fr.ecareus.onlinedoc.ui.pages;

import fr.ecareus.onlinedoc.ui.PanelManager;
import fr.ecareus.onlinedoc.ui.shared.Dialog;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class UserAccountPage extends Dialog {

    @Override
    public void init(PanelManager manager) {
        super.init(manager);
        this.setTitle("Informations utilisateur");

        // Center : User's information as a form
        GridPane contentPane = new GridPane();
        contentPane.setStyle("-fx-background-color: rgb(35, 40, 40);");
        setCanTakeAllSize(contentPane);

        TextField userNameTxt = new TextField(this.currentUser.getName());
        userNameTxt.setMaxWidth(300);
        Label userNameLbl = new Label("Nom d'utilisateur :");
        userNameLbl.setFont(Font.font("Consolas", FontWeight.LIGHT, FontPosture.REGULAR, 14f));
        userNameLbl.setStyle("-fx-text-fill: white;");

        setCenterH(userNameLbl);
        setCenterV(userNameLbl);
        userNameLbl.setTranslateY(-25);
        setCenterH(userNameTxt);
        setCenterV(userNameTxt);
        setCanTakeAllSize(userNameLbl);
        setCanTakeAllSize(userNameTxt);

        contentPane.getChildren().addAll(userNameLbl, userNameTxt);


        // Bottom : Action Buttons
        GridPane actionPane = new GridPane();
        actionPane.setStyle("-fx-background-color: rgb(35, 40, 40);" +
                "-fx-border-width: 1 0 0 0;" +
                "-fx-border-style: solid;" +
                "-fx-border-color: gray;");
        setCanTakeAllSize(actionPane);

        Button cancelBtn = new Button("Annuler");
        Button validateBtn = new Button("Valider");

        setRight(cancelBtn);
        setRight(validateBtn);
        cancelBtn.setTranslateX(-100);
        validateBtn.setTranslateX(-15);
        setCanTakeAllSize(cancelBtn);
        setCanTakeAllSize(validateBtn);

        cancelBtn.setOnMouseClicked(e -> this.stage.close());
        validateBtn.setOnMouseClicked(e -> {
            this.manager.getUser().setName(userNameTxt.getText());
            this.manager.saveConfig();
            this.manager.refresh();
            this.stage.close();
        });
        actionPane.getChildren().addAll(cancelBtn, validateBtn);

        this.layout.add(contentPane, 0, 1);
        this.layout.add(actionPane, 0, 2);
        this.onShow();
    }

    @Override
    public String getName() {
        return "UserAccountPage";
    }
}
