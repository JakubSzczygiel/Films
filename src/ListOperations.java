import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListOperations {

    public List<Director> extractDirectorsFromList(List<Film> films) {
        List<Director> directors = new ArrayList<>();
        for (Film film : films
        ) {
            directors.add(film.director);
        }
        //System.out.println(directors);
        return directors;
    }

    public Set<Director> printMostPopularDirector(List<Director> directors) {
        Set<Director> mostPopularDirectors = new HashSet<>();

        int maxNumberOffOscars = 0;
        for (Director director : directors
        ) {
            for (Director director2 : directors
            ) {
                if (director.equals(director2)) {
                    director.numberOfOscars++;
                    if (maxNumberOffOscars < director.numberOfOscars) {
                        maxNumberOffOscars = director.numberOfOscars;
                    }
                }
            }
        }

        for (Director director : directors
        ) {
            if (director.numberOfOscars == maxNumberOffOscars)
                mostPopularDirectors.add(director);
        }
        System.out.println("Most popular Directors: " + mostPopularDirectors);
        return mostPopularDirectors;
    }


}
