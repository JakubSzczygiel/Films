import java.util.Objects;

public class Director {
    public String directorName;
    public String directorLastName;


    public Director(String directorName, String directorLastName) {
        this.directorName = directorName;
        this.directorLastName = directorLastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(directorName, director.directorName) &&
                Objects.equals(directorLastName, director.directorLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directorName, directorLastName);
    }

    @Override
    public String toString() {
        return directorName + " " + directorLastName;
    }
}
