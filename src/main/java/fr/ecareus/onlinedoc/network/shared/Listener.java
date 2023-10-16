package fr.ecareus.onlinedoc.network.shared;

import fr.ecareus.onlinedoc.network.SessionManager;
import fr.ecareus.onlinedoc.ui.PanelManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public abstract class Listener extends Thread {

    protected final InetAddress groupIp;
    protected final int port;
    protected final PanelManager manager;
    protected MulticastSocket socket;

    protected final SessionManager session;

    public Listener(InetAddress groupIp, int port, PanelManager manager, SessionManager session) throws IOException {
        this.groupIp = groupIp;
        this.port = port;
        this.manager = manager;
        this.session = session;
    }

    @Override
    public void run() {
        super.run();
    }
}
