package fr.ecareus.onlinedoc.network;

import fr.ecareus.onlinedoc.network.shared.DataType;
import fr.ecareus.onlinedoc.network.shared.Listener;
import fr.ecareus.onlinedoc.ui.PanelManager;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Transmitter extends Listener {

    public Transmitter(InetAddress groupIp, int port, PanelManager manager) throws IOException {
        super(groupIp, port, manager);

        this.socket = new MulticastSocket();
        this.socket.setTimeToLive(15);
    }

    public void transmit(String data, DataType type) throws IOException {
        byte[] dataAsByte;
        DatagramPacket message;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new DataOutputStream(out).writeUTF(type.name() + ":" + data);

        dataAsByte = out.toByteArray();
        message = new DatagramPacket(dataAsByte, dataAsByte.length, this.groupIp, this.port);
        this.socket.send(message);
    }

    public void askAvailableSesions() throws IOException {
        this.transmit("userId=" + this.manager.getUser().getId().toString(), DataType.SESSION_LIST);
    }
}
