import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {
    public void writeToJson(List list, String path) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        FileWriter writer = new FileWriter(path);
        writer.write(json);
        writer.close();

    }
}
