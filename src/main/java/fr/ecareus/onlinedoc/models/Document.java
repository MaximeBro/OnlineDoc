package fr.ecareus.onlinedoc.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Document {

    private final UUID id;
    private String title;
    private List<User> users;
    private List<String> content;


    public Document(String title, User author) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.users = new ArrayList<>();
        this.users.add(author);
        this.content = new ArrayList<>();
    }

    public UUID getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<User> getUsers() { return users; }

    public List<String> getContent() { return content; }
}
