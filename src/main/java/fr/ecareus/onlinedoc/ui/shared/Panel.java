package fr.ecareus.onlinedoc.ui.shared;

import fr.ecareus.onlinedoc.ui.PanelManager;
import javafx.animation.FadeTransition;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

import java.lang.reflect.Type;

public abstract class Panel implements IPanel, IPosition {

    protected GridPane layout = new GridPane();
    protected PanelManager manager;

    public Panel() {
        this.layout = new GridPane();
    }

    public void init(PanelManager manager) {
        this.manager = manager;
        setCanTakeAllSize(this.layout);
    }

    public Type getType() { return super.getClass(); }

    @Override
    public GridPane getLayout() { return this.layout; }

    @Override
    public String getStyleSheetFile() { return null; }

    @Override
    public abstract String getName();

    public void refresh() {};

    @Override
    public void onShow() {
        FadeTransition transition = new FadeTransition(Duration.seconds(0.5d), this.layout);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setAutoReverse(true);
        transition.play();
    }

    @Override
    public void setLeft(Node node) { GridPane.setHalignment(node, HPos.LEFT); }

    public void setRight(Node node) { GridPane.setHalignment(node, HPos.RIGHT); }

    @Override
    public void setTop(Node node) { GridPane.setValignment(node, VPos.TOP); }

    @Override
    public void setBottom(Node node) { GridPane.setValignment(node, VPos.BOTTOM); }

    @Override
    public void setBaseLine(Node node) { GridPane.setValignment(node, VPos.BASELINE); }

    @Override
    public void setCenterH(Node node) { GridPane.setHalignment(node, HPos.CENTER); }

    @Override
    public void setCenterV(Node node) { GridPane.setValignment(node, VPos.CENTER); }

    public void setCanTakeAllSize(Node node) {
        GridPane.setHgrow(node, Priority.ALWAYS);
        GridPane.setVgrow(node, Priority.ALWAYS);
    }

    public void setCanTakeAllWidth(Node ... nodes) {
        for(Node n : nodes) {
            GridPane.setHgrow(n, Priority.ALWAYS);
        }
    }
}
