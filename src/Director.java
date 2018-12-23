import java.util.Objects;

public class Director {
    public String directorName;
    public String directorLastName;
    int numberOfOscars;


    public Director(String directorName, String directorLastName) {
        this.directorName = directorName;
        this.directorLastName = directorLastName;
    }

    @Override
    public String toString() {
        return  directorName + " " + directorLastName + " "  + numberOfOscars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return numberOfOscars == director.numberOfOscars &&
                Objects.equals(directorName, director.directorName) &&
                Objects.equals(directorLastName, director.directorLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directorName, directorLastName);
    }
}
