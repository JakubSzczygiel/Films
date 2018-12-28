import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonReader {

    public List<Film> readFilmsFromJson(String path) throws IOException {
        Reader reader = new FileReader(path);
        List<Film> films = new ArrayList<>();
        Gson gson = new Gson();
        Film[] filmArray = gson.fromJson(reader, Film[].class);
        Collections.addAll(films, filmArray);
        return films;
    }

    public List<Director> readDirectorsFromJson(String path) throws IOException {
        ListOperations listOperations = new ListOperations();
        List<Film> films;
        List<Director> directors;

        films = readFilmsFromJson(path);
        directors = listOperations.extractDirectorsFromList(films);
        return directors;
    }

}



