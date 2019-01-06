public class Film {

    public int year;
    public String filmName;
    public Director director;

    public Film(int year, String filmName, String directorName, String directorLastname) {
        this.year = year;
        this.filmName = filmName;
        this.director = new Director(directorName, directorLastname);
    }

    public Object[] getObjectArrayFromFilmFields() {
        Object[] objectArratFromFilmFields = {year, filmName, director};
        return objectArratFromFilmFields;
    }

    @Override
    public String toString() {
        return year + " | " + filmName + " | " + director + "\n";
    }
}
