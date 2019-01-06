import java.io.IOException;
import java.util.List;

public interface Writeable {
    void write(List<Film> list) throws IOException;
}
