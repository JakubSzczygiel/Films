import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {
    public void writeToJson(List list, String path) throws IOException {
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);
        FileWriter writer = new FileWriter(path);
        writer.write(json);
        writer.close();
    }
}
