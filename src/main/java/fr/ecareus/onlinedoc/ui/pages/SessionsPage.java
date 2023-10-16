package fr.ecareus.onlinedoc.ui.pages;

import fr.ecareus.onlinedoc.models.AvailableSession;
import fr.ecareus.onlinedoc.ui.PanelManager;
import fr.ecareus.onlinedoc.ui.shared.Panel;

import java.util.List;

public class SessionsPage extends Panel {

    @Override
    public void init(PanelManager manager) {
        super.init(manager);
        List< AvailableSession> sessions = this.manager.getSessions();

        // For each session -> display a selectable card

        // Add join and cancel buttons
    }

    @Override
    public String getName() {
        return "SessionsPage";
    }
}
