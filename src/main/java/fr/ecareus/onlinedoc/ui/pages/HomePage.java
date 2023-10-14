package fr.ecareus.onlinedoc.ui.pages;

import fr.ecareus.onlinedoc.models.Document;
import fr.ecareus.onlinedoc.ui.PanelManager;
import fr.ecareus.onlinedoc.ui.shared.Panel;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class HomePage extends Panel {

    private Document currentDocument;
    private Label title;

    public HomePage() {
        this.currentDocument = null;
    }

    @Override
    public void init(PanelManager manager) {
        super.init(manager);

        if(this.currentDocument == null) {
            title = new Label("Bienvenue sur OnlineDoc " + this.manager.getUser().getName() + " !");
            title.setFont(Font.font("Consolas", FontWeight.LIGHT, FontPosture.REGULAR, 24f));
            title.setStyle("-fx-text-fill: white;");
            setCanTakeAllSize(title);
            setCenterV(title);
            setCenterH(title);

            Label subTitle = new Label("Créer un document ou rejoignez une session pour démarrer l'édition...");
            subTitle.setFont(Font.font("Consolas", FontWeight.LIGHT, FontPosture.ITALIC, 18f));
            subTitle.setStyle("-fx-text-fill: gray;");
            setCanTakeAllSize(subTitle);
            setCenterV(subTitle);
            setCenterH(subTitle);
            subTitle.setTranslateY(40);

            this.layout.getChildren().addAll(title, subTitle);
        }
    }

    @Override
    public String getName() {
        return "HomePage";
    }

    @Override
    public void refresh() {
        this.title.setText("Bienvenue sur OnlineDoc " + this.manager.getUser().getName() + " !");
    }
}
