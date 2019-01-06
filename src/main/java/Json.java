import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Json implements Writeable, Readable {

    static final String JSON_FILE_PATH = "C:\\Users\\Jakub\\IdeaProjects\\Films\\Output.json";


    @Override
    public void write(List<Film> list) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);
        FileWriter writer = new FileWriter(JSON_FILE_PATH);
        writer.write(json);
        writer.close();
    }

    @Override
    public List read() throws IOException {
        Reader reader = new FileReader(JSON_FILE_PATH);
        List<Film> films = new ArrayList<>();
        Gson gson = new Gson();
        Film[] filmArray = gson.fromJson(reader, Film[].class);
        Collections.addAll(films, filmArray);
        return films;
    }
}

