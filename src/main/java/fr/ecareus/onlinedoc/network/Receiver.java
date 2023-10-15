package fr.ecareus.onlinedoc.network;

import fr.ecareus.onlinedoc.network.shared.DataType;
import fr.ecareus.onlinedoc.network.shared.Listener;
import fr.ecareus.onlinedoc.ui.PanelManager;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver extends Listener {

    public Receiver(InetAddress groupIp, int port, PanelManager manager) throws IOException {
        super(groupIp, port, manager);

        this.socket = new MulticastSocket(this.port);
        this.socket.joinGroup(this.groupIp);
    }

    @Override
    public void run() {
        DatagramPacket message;
        byte[] messageAsByte;
        String data;

        while(true) {
            messageAsByte = new byte[1024];
            message = new DatagramPacket(messageAsByte, messageAsByte.length);

            try {
                this.socket.receive(message);
                data = new DataInputStream(new ByteArrayInputStream(messageAsByte)).readUTF();

                DataType type = Enum.valueOf(DataType.class, data.split(":")[0]);
                // TO-DO : Switch DataType do treatment...

                switch(type) {
                    case SESSION_LIST -> {

                    }

                    default -> { }
                }

            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
