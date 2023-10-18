package fr.ecareus.onlinedoc.ui.pages;

import fr.ecareus.onlinedoc.models.AvailableSession;
import fr.ecareus.onlinedoc.models.Document;
import fr.ecareus.onlinedoc.ui.PanelManager;
import fr.ecareus.onlinedoc.ui.components.Card;
import fr.ecareus.onlinedoc.ui.shared.Dialog;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class SessionsPage extends Dialog {

    private AvailableSession selectedSession;
    private List<Card> cards;

    @Override
    public void init(PanelManager manager) {
        super.init(manager);
        this.setTitle("Sessions en local");
        this.stage.getScene().getStylesheets().clear();
        this.stage.getScene().getStylesheets().add("css/Card.css");
        List<AvailableSession> sessions = this.testSessions(); // this.manager.getSessions();
        this.cards = new ArrayList<>();


        // Center : Available sessions as selectable cards
        GridPane contentPane = new GridPane();
        ScrollPane scrollPane = new ScrollPane(contentPane);
        contentPane.setStyle("-fx-background-color: rgb(35, 40, 40);");
        setCanTakeAllSize(contentPane);
        if(sessions.size() == 0) {
            Label noSessionLbl = new Label("Nous n'avons pas trouvé de session sur le réseau local...");
            noSessionLbl.setFont(Font.font("Consolas", FontWeight.LIGHT, FontPosture.ITALIC, 16f));
            noSessionLbl.setStyle("-fx-text-fill: gray;");
            setCanTakeAllSize(noSessionLbl);
            setCenterV(noSessionLbl);
            setCenterH(noSessionLbl);

            contentPane.getChildren().add(noSessionLbl);
        } else {
            // TO-DO : For each session -> display a selectable card
            int x = 0;
            int y = 0;
            for(AvailableSession session : sessions) {
                if(x > 2) {
                    x = 0;
                    y++;
                }
                Card card = new Card(session);
                this.cards.add(card);
                contentPane.add(card.getLayout(), x, y);
                x++;
            }

            contentPane.setHgap(20);
            contentPane.setVgap(40);
            contentPane.setPadding(new Insets(50, 30, 50, 30));
            scrollPane.setFitToHeight(true);
            scrollPane.setFocusTraversable(false);
        }


        // Bottom : Action Buttons
        GridPane actionPane = new GridPane();
        actionPane.setStyle("-fx-background-color: rgb(35, 40, 40);" +
                            "-fx-border-width: 1 0 0 0;" +
                            "-fx-border-style: solid;" +
                            "-fx-border-color: gray;");
        setCanTakeAllSize(actionPane);

        Button cancelBtn = new Button("Annuler");
        Button joinBtn = new Button("Rejoindre");

        setRight(cancelBtn);
        setRight(joinBtn);
        cancelBtn.setTranslateX(-100);
        joinBtn.setTranslateX(-15);
        setCanTakeAllSize(cancelBtn);
        setCanTakeAllSize(joinBtn);

        joinBtn.setDisable(this.selectedSession == null);

        cancelBtn.setOnMouseClicked(e -> this.stage.close());
        joinBtn.setOnMouseClicked(e -> this.stage.close());

        for(Card card : this.cards) {
            card.getLayout().setOnMouseClicked(e -> {
                this.selectedSession = card.getSession();
                card.setSelected(true);
                joinBtn.setDisable(false);
                for(Card card2 : this.cards)
                    if(card.getId() != card2.getId())
                        card2.setSelected(false);
            });
        }

        actionPane.getChildren().addAll(cancelBtn, joinBtn);

        this.layout.add(scrollPane, 0, 1);
        this.layout.add(actionPane, 0, 2);
        this.onShow();
    }

    private List<AvailableSession> testSessions() {
        List<AvailableSession> list = new ArrayList<>();
        for(Document doc : this.manager.getUser().getDocuments()) {
            list.add(new AvailableSession(doc.getId(), doc.getTitle(), doc.getUsers().size()));
        }

        return list;
    }

    @Override
    public String getName() {
        return "SessionsPage";
    }
}
