package fr.ecareus.onlinedoc.models;

import java.util.UUID;

public class AvailableSession {

    private final UUID documentId;
    private final String title;
    private final int users;

    public AvailableSession(UUID documentId, String title, int connectedUsers) {
        this.documentId = documentId;
        this.title = title;
        this.users = connectedUsers;
    }

    public UUID getDocumentId() { return documentId; }

    public String getTitle() { return title; }

    public int getUsers() { return users; }
}
