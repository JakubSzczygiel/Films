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

    public UrlReader(String url) {
        this.url = url;
    }

    public List getFilmsList() throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("font");
        int x = 0;
        for (int i = 0; i < links.size(); i++) {
            Element link = links.get(i);
            if (link.text().equals(String.valueOf(1960 + x))) {
                x++;
                /*link.text() - it is date of production; links.get(i+1) - it is name of a film; links.get(i+2) - it is a director */
                String[] director = links.get(i + 2).text().split(" ");

                if (director.length > 2 && !(director[2].equals("and"))) {
                    if (director[2].endsWith("*")) {
                        director[2] = director[2].substring(0, director[2].length() - 1);
                    }
                    films.add(new Film(Integer.parseInt(link.text()), links.get(i + 1).text(), director[0], director[1] + " " + director[2]));
                } else {
                    if (director[1].endsWith("*")) {
                        director[1] = director[1].substring(0, director[1].length() - 1);
                    }
                    films.add(new Film(Integer.parseInt(link.text()), links.get(i + 1).text(), director[0], director[1]));
                }
            }

        }
        //System.out.println(films);
        return films;
    }
}




