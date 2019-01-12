import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        Map<Director, Integer> directorsMap = fillDirectorsWithNumberOfOscars(directors);
        int maxNumberOfOscars = returnMaxNumberOfOscars(directorsMap);
        List<Director> mostPopularDirectors = returnDirectorsWithSpecificValueOfOscars(maxNumberOfOscars, directorsMap);
        System.out.println("Most popular Directors (with " + maxNumberOfOscars + " Oscars) : " + mostPopularDirectors);
    }

    public String findDirectorLastName(String[] director) {
        String directorLastName = "";
        for (int i = 1; i < director.length; i++) {
            if (i != 1) {
                directorLastName += " ";
            }
            directorLastName += director[i];
        }
        return directorLastName;
    }

    private Map<Director, Integer> fillDirectorsWithNumberOfOscars(List<Director> directors) {
        Set<Director> directorsTemp = new HashSet<>();
        Map<Director, Integer> directorMap = new HashMap<>();
        directorsTemp.addAll(directors);
        int numberOfOscars = 0;

        for (Director director : directorsTemp
        ) {
            while (directors.remove(director)) {
                numberOfOscars++;
            }
            directorMap.put(director, numberOfOscars);
            numberOfOscars = 0;
        }
        return directorMap;
    }

    private int returnMaxNumberOfOscars(Map<Director, Integer> directorsMap) {
        int maxNumberOfOscars = 0;
        while (directorsMap.containsValue(maxNumberOfOscars + 1)) {
            maxNumberOfOscars++;
        }
        return maxNumberOfOscars;
    }

    private List<Director> returnDirectorsWithSpecificValueOfOscars(int numberOfOscars, Map<Director, Integer> directorsMap) {
        List<Director> directorsWithSpecificValueOfOscars = new ArrayList<>();
        List<Director> directors = new ArrayList<>();
        directors.addAll(directorsMap.keySet());
        for (Director director : directors
        ) {
            if (directorsMap.get(director) == numberOfOscars) {
                directorsWithSpecificValueOfOscars.add(director);
            }
        }
        return directorsWithSpecificValueOfOscars;
    }

}
