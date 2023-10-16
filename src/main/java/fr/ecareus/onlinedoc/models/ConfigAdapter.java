package fr.ecareus.onlinedoc.models;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConfigAdapter extends TypeAdapter<Config> {
    @Override
    public void write(JsonWriter writer, Config config) throws IOException {
        User user = config.getUser();

        writer.beginObject();

        // User
        writer.name("user");
        writer.beginObject();
        writer.name("userId");
        writer.value(user.getId().toString());
        writer.name("userName");
        writer.value(user.getName());
        writer.name("documentsFolder");
        writer.value(user.getDocumentsPath());
        writer.endObject();

        // Documents
        writer.name("documents");
        writer.beginArray();
        for(Document document : config.getDocuments()) {
            writer.beginObject();
            writer.name("id");
            writer.value(document.getId().toString());
            writer.name("title");
            writer.value(document.getTitle());
            writer.endObject();
        }
        writer.endArray();

        writer.endObject();
    }

    @Override
    public Config read(JsonReader reader) throws IOException {
        Config config = new Config();
        String fieldName = "";
        reader.beginObject();

        while(reader.hasNext()) {
            JsonToken token = reader.peek();
            if(token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }

            if(fieldName.equals("user")) {
                token = reader.peek();
                reader.beginObject();
                fieldName = reader.nextName();
                UUID id = UUID.fromString(reader.nextString());
                fieldName = reader.nextName();
                String name = reader.nextString();
                fieldName = reader.nextName();
                String path = reader.nextString();

                config.setUser(new User(id, name, path));
                reader.endObject();
            }

            if(fieldName.equals("documents")) {
                token = reader.peek();

                List<Document> documents = new ArrayList<>();

                reader.beginArray();
                while(reader.hasNext()) {
                    reader.beginObject();
                    fieldName = reader.nextName();
                    UUID id = UUID.fromString(reader.nextString());
                    fieldName = reader.nextName();
                    String title = reader.nextString();
                    documents.add(new Document(id, title, config.getUser()));
                    reader.endObject();
                }
                reader.endArray();
                config.setDocuments(documents);
            }
        }

        reader.endObject();
        return config;
    }
}
