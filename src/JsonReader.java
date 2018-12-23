import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonReader {

    public List<Film> readFromJson(String path) throws IOException {
            Reader reader = new FileReader(path);
            List<Film> films=new ArrayList<>();
            Gson gson=new Gson();
            Film[] filmArray = gson.fromJson(reader, Film[].class);
            Collections.addAll(films, filmArray);
            //System.out.println("Read from JSON \n" + films);
            return films;
    }

}
