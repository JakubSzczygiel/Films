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
        return directors;
    }

    public void printMostPopularDirector(List<Director> directors) {
        fillDirectorsWithNumberOfOscars(directors);
        int maxNumberOfOscars = returnMaxNumberOfOscars(directors);
        List<Director> mostPopularDirectors = returnDirectorsWithSpecificValueOfOscars(maxNumberOfOscars, directors);
        System.out.println("Most popular Directors: " + mostPopularDirectors);
    }

    private void fillDirectorsWithNumberOfOscars(List<Director> directors) {
        Set<Director> directorsTemp = new HashSet<>();
        directorsTemp.addAll(directors);

        for (Director director : directorsTemp
        ) {
            while (directors.remove(director)) {
                director.numberOfOscars++;
            }
        }
        directors.clear();
        directors.addAll(directorsTemp);
    }

    private int returnMaxNumberOfOscars(List<Director> directors) {
        Set<Director> directorsSet = new HashSet<>();
        directorsSet.addAll(directors);
        int maxNumberOfOscars = 0;
        for (Director director : directorsSet
        ) {
            maxNumberOfOscars = director.numberOfOscars > maxNumberOfOscars ? director.numberOfOscars : maxNumberOfOscars;
        }
        return maxNumberOfOscars;
    }

    private List<Director> returnDirectorsWithSpecificValueOfOscars(int numberOfOscars, List<Director> directors) {
        List<Director> directorsWithSpecificValueOfOscars = new ArrayList<>();

        for (Director director : directors
        ) {
            if (director.numberOfOscars == numberOfOscars)
                directorsWithSpecificValueOfOscars.add(director);
        }
        return directorsWithSpecificValueOfOscars;
    }

}
