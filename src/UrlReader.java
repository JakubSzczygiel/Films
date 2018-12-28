import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UrlReader {
    String url;
    List<Film> films = new ArrayList<>();
    public static final int YEAR_OF_FIRST_FILM_PRODUCTION = 1960;


    public UrlReader(String url) {
        this.url = url;
    }

    public List getFilmsList() throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements textLines = doc.select("font");
        int iterator = 0;
        for (int i = 0; i < textLines.size(); i++) {
            Element textLine = textLines.get(i);
            if (textLine.text().equals(String.valueOf(YEAR_OF_FIRST_FILM_PRODUCTION + iterator))) {
                iterator++;
                int filmYear = Integer.parseInt(textLine.text());
                String filmName = textLines.get(i + 1).text();
                String[] directors = returnDirectorString(textLines.get(i + 2).text().split(" "));
                String directorName = directors[0];
                String directorLastName = directors[1];
                films.add(new Film(filmYear, filmName, directorName, directorLastName));
            }
        }
        //System.out.println(films);
        return films;
    }

    private String[] returnDirectorString(String[] director) {
        String[] directorStringArray = new String[2];
        directorStringArray[0] = director[0];

        if (hasMoreThanOneLastName(director)) {
            directorStringArray[1] = director[1] + " " + director[2];
        } else {
            directorStringArray[1] = director[1];
        }
        removeAsterisk(directorStringArray);
        return directorStringArray;
    }

    private boolean hasMoreThanOneLastName(String[] director) {
        return director.length > 2 && !(director[2].equals("and"));
    }

    private void removeAsterisk(String[] director) {
        if (director[1].endsWith("*")) {
            director[1] = director[1].substring(0, director[1].length() - 1);
        }
    }

}




