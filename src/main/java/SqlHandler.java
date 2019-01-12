import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SqlHandler implements Writeable, Readable {

    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DATABASE_URL = "jdbc:sqlite:C:/Users/Jakub/IdeaProjects/Database/";
    static final String DATABASE_NAME = "FILMSDATABASE.db";
    static final String TABLE_NAME = "Films";

    @Override
    public List read() {
        List<Film> films = new ArrayList<>();
        Connection connection = null;
        try {
            connection = createConnection(DATABASE_NAME);
            films = readFilms(connection, TABLE_NAME);
            dropTable(TABLE_NAME, connection);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }

    private void dropTable(String tableName, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("DROP TABLE %s", tableName));
        statement.close();
    }

    private List<Film> readFilms(Connection connection, String tableName) throws SQLException {
        List<Film> films = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s", tableName));
        while (resultSet.next()) {
            int filmYear = resultSet.getInt("filmYear");
            String filmName = resultSet.getString("filmName");
            String[] director = resultSet.getString("director").split(" ");
            String directorName = director[0];
            String directorLastName = findDirectorLastName(director);
            films.add(new Film(filmYear, filmName, directorName, directorLastName));
        }
        statement.close();
        return films;
    }

    private String findDirectorLastName(String[] director) {
        String directorLastName = "";
        for (int i = 1; i < director.length; i++) {
            if (i != 1) {
                directorLastName += " ";
            }
            directorLastName += director[i];
        }
        return directorLastName;
    }

    @Override
    public void write(List<Film> films) {
        try {
            Connection connection = createConnection(DATABASE_NAME);
            createTableInDatabase(connection, TABLE_NAME);
            addListToDatabase(connection, films, TABLE_NAME);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableInDatabase(Connection connection, String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("CREATE TABLE IF NOT EXISTS %s " +
                "(filmYear INTEGER, " +
                " filmName VARCHAR, " +
                " director VARCHAR)", tableName));
        statement.close();
    }


    private void addListToDatabase(Connection connection, List<Film> films, String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        for (Film film : films
        ) {
            String filmName = "\"" + film.filmName + "\"";
            String director = "\"" + film.director.directorName + " " + film.director.directorLastName + "\"";
            statement.executeUpdate(String.format("INSERT INTO %s (filmYear, filmName, director) " +
                    "VALUES (%d, %s, %s)", tableName, film.year, filmName, director));
        }
        statement.close();
    }


    private Connection createConnection(String databaseName) throws SQLException {

        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL + databaseName);
            if (connection != null) {
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public List<Director> getDirectorsWithMaxNumberOfOscars() {
        List<Director> directors = new ArrayList<>();
        try {
            Connection connection = createConnection(DATABASE_NAME);
            getMaxNumberOfOscars(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return directors;
    }

    private void getMaxNumberOfOscars(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();


    }

}

