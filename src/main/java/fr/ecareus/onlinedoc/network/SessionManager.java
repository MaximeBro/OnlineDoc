package fr.ecareus.onlinedoc.network;

import fr.ecareus.onlinedoc.ui.PanelManager;

import java.io.IOException;
import java.net.InetAddress;

public class SessionManager {

    private final PanelManager manager;
    private final Transmitter transmitter;
    private final Receiver receiver;
    private final InetAddress groupIp;
    private final int port;

    public SessionManager(PanelManager manager) throws IOException {
        this.manager = manager;
        this.groupIp = InetAddress.getByName("230.125.10.15");
        this.port = 2009;
        this.transmitter = new Transmitter(this.groupIp, this.port, this.manager, this);
        this.receiver = new Receiver(this.groupIp, this.port, this.manager, this);
    }

    public void init() {
        this.transmitter.start();
        this.receiver.start();
    }

    public Transmitter getTransmitter() { return this.transmitter; }
    public Receiver getReceiver() { return this.receiver; }
}
