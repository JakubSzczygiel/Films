import java.sql.*;
import java.io.IOException;
import java.util.List;

public class SqlHandler implements Writeable, Readable {

    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DATABASE_URL = "jdbc:sqlite:C:/Users/Jakub/IdeaProjects/Database/";


    @Override
    public List read() throws IOException {
        return null;
    }

    @Override
    public void write(List<Film> films) {

        try {
            String databaseName = "testDatabase.db";
            String tableName = "Films";
            Connection connection = createDatabase(databaseName);
            createTableInDatabase(connection, tableName);
            addListToDatabase(connection, films, tableName);
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
    }


    private Connection createDatabase(String databaseName) throws SQLException {

        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL + databaseName);
            if (connection != null) {
                System.out.println(databaseName + " was created");
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}

