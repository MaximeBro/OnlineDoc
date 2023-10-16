package fr.ecareus.onlinedoc.network;

import fr.ecareus.onlinedoc.models.AvailableSession;
import fr.ecareus.onlinedoc.models.Document;
import fr.ecareus.onlinedoc.network.shared.DataType;
import fr.ecareus.onlinedoc.network.shared.Listener;
import fr.ecareus.onlinedoc.ui.PanelManager;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.UUID;

public class Receiver extends Listener {

    public Receiver(InetAddress groupIp, int port, PanelManager manager, SessionManager session) throws IOException {
        super(groupIp, port, manager, session);

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

                // Check if the user is not the same thread as we

                DataType type = Enum.valueOf(DataType.class, data.split(":")[0]);
                // TO-DO : Switch DataType do treatment...

                Document currentDocument = this.manager.getCurrentDocument();
                switch(type) {
                    case SESSION_LIST -> {
                        if(currentDocument != null)
                            this.session.getTransmitter().transmit("document:" + currentDocument.getId() + "," + currentDocument.getTitle() + "," + currentDocument.getUsers().size(),
                                                                    DataType.SESSION_AVAILABLE);
                    }

                    case SESSION_AVAILABLE -> {
                        data.replaceFirst(DataType.SESSION_AVAILABLE.name(), "");
                        data.replaceFirst("document:", "");
                        String[] values = data.split(",");
                        AvailableSession session = new AvailableSession(UUID.fromString(values[0]), values[1], Integer.parseInt(values[2]));
                        this.manager.addSession(session);
                    }

                    default -> { }
                }

            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
