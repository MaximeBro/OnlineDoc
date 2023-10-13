package fr.ecareus.onlinedoc.ui.shared;

import javafx.scene.Node;

public interface IPosition {
    void setLeft(Node node);
    void setRight(Node node);
    void setTop(Node node);
    void setBottom(Node node);
    void setBaseLine(Node node);
    void setCenterH(Node node);
    void setCenterV(Node node);
}
