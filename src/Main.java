import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.filmsite.org/bestpics4.html";
        String jsonFilePath = "C:\\Users\\Jakub\\IdeaProjects\\Films\\Output.json";
        UrlReader urlReader = new UrlReader(url);
        JsonWriter jsonWriter = new JsonWriter();
        JsonReader jsonReader = new JsonReader();
        ListOperations listOperations=new ListOperations();

        try {
            jsonWriter.writeToJson(urlReader.getFilmsList(), jsonFilePath);
            listOperations.printMostPopularDirector(listOperations.extractDirectorsFromList(jsonReader.readFromJson(jsonFilePath)));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


