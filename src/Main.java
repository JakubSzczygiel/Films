import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.filmsite.org/bestpics4.html";
        String jsonFilePath = "C:\\Users\\Jakub\\IdeaProjects\\Films\\Output.json";
        UrlReader urlReader = new UrlReader(url);
        JsonWriter jsonWriter = new JsonWriter();
        JsonReader jsonReader = new JsonReader();
        ListOperations listOperations = new ListOperations();
        List<Film> films;
        List<Director> directorsReadFromJson;


        try {
            films = urlReader.getFilmsList();
            jsonWriter.writeToJson(films, jsonFilePath);
            directorsReadFromJson = jsonReader.readDirectorsFromJson(jsonFilePath);
            listOperations.printMostPopularDirector(directorsReadFromJson);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


