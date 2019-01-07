
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();

        String url = "https://www.filmsite.org/bestpics4.html";
        Json json = new Json();
        Excel excel = new Excel();

        try {
            findMostPopularDirectors(url, json, json);
            findMostPopularDirectors(url, excel, excel);

        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("time of program execution: " + (endTime - beginTime) + " ms");
    }

    static void findMostPopularDirectors(String url, Writeable writeable, Readable readable) throws IOException {
        UrlReader urlReader = new UrlReader(url);
        ListOperations listOperations = new ListOperations();
        List<Film> films;
        List<Director> directorsReadFromFile;
        List<Film> filmsReadFromFile;


        films = urlReader.getFilmsList();
        writeToFile(writeable, films);
        filmsReadFromFile = readFromFile(readable);
        directorsReadFromFile = listOperations.extractDirectorsFromList(filmsReadFromFile);
        System.out.println(readable.getClass().getName());
        listOperations.printMostPopularDirector(directorsReadFromFile);
    }

    static void writeToFile(Writeable fileFormat, List list) throws IOException {
        fileFormat.write(list);
    }

    static List readFromFile(Readable fileFormat) throws IOException {
        return fileFormat.read();
    }

}


