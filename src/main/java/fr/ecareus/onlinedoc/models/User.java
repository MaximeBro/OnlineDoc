package fr.ecareus.onlinedoc.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private final UUID id;
    private String name;
    private List<Document> documents;
    private String docsFolder;

    public User(String path) {
        this.id = UUID.randomUUID();
        this.name = "user-" + this.id;
        this.documents = new ArrayList<>();
        this.docsFolder = path;
    }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public UUID getId() { return this.id; }

    public String getDocumentsPath() { return this.docsFolder; }
    public void setFolderPath(String path) { this.docsFolder = path; }

    public List<Document> getDocuments() { return this.documents; }
}
