package fr.ecareus.onlinedoc.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.List;

public class Config {
    private User user;
    private List<Document> documents;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Config.class, new ConfigAdapter()).create();

    public Config() { }
    public Config(User user) {
        this.user = user;
        this.documents = this.user.getDocuments();
    }

    public void init(String path) {
        File configFolder = new File(path);
        File docsFolder = new File(path + "\\Documents");
        File configFile = new File(path + "\\config.json");

        if(!configFolder.exists())
            configFolder.mkdir();

        if(!docsFolder.exists())
            docsFolder.mkdir();

        if(!configFile.exists()) {
            try {
                configFile.createNewFile();
                Writer writer = new FileWriter(configFile, false);
                String data = this.gson.toJson(this);
                writer.write(data);
                writer.flush();
                writer.close();
            } catch (IOException e) { e.printStackTrace(); }
        } else {
            try {
                Reader reader = new FileReader(configFile);
                Config config = this.gson.fromJson(reader, Config.class);
                this.user = config.getUser();
                this.documents = config.getDocuments();
                reader.close();
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    public void save(String path) {
        try {
            Writer writer = new FileWriter(new File(path + "\\config.json"));
            String data = gson.toJson(this);
            writer.write(data);
            writer.flush();
            writer.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public User getUser() { return this.user; }
    public void setUser(User user) { this.user = user; }

    public List<Document> getDocuments() { return this.documents; }
    public void setDocuments(List<Document> documents) { this.documents = documents; }
}
