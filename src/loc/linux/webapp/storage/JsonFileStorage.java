package loc.linux.webapp.storage;

import loc.linux.webapp.model.Resume;
import loc.linux.webapp.util.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by papa on 11.03.2016.
 */
public class JsonFileStorage extends FileStorage {
    public JsonFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume r) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            JsonParser.write(r, w);
        }
    }

    protected Resume read(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return JsonParser.read(r, Resume.class);
        }
    }
}
