import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Excel implements Readable, Writeable {

    static final String EXCEL_FILE_PATH = "C:\\Users\\Jakub\\IdeaProjects\\Films\\Output.xlsx";

    @Override
    public List read() throws IOException {
        List<Film> films;
        FileInputStream inputStream = new FileInputStream(new File(EXCEL_FILE_PATH));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        films = getRowsValues(rowIterator);

        workbook.close();
        inputStream.close();
        return films;
    }

    private List<Film> getRowsValues(Iterator<Row> rowIterator) {
        List<Film> films = new ArrayList<>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) {
                continue;
            }
            Iterator<Cell> cellIterator = row.cellIterator();
            Map<String, String> film = getCellsValues(cellIterator);
            films.add(new Film(Integer.parseInt(film.get("filmYear")), film.get("filmName"), film.get("directorName"), film.get("directorLastName")));
        }
        return films;
    }


    private Map<String, String> getCellsValues(Iterator<Cell> cellIterator) {
        Map<String, String> film = new HashMap<>();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            switch (cell.getColumnIndex()) {
                case 0:
                    film.put("filmYear", cell.getStringCellValue());
                    break;
                case 1:
                    film.put("filmName", cell.getStringCellValue());
                    break;
                case 2:
                    ListOperations listOperation = new ListOperations();
                    String[] director = cell.getStringCellValue().split(" ");
                    film.put("directorName", director[0]);
                    film.put("directorLastName", listOperation.findDirectorLastName(director));
                    break;
            }
        }
        return film;
    }


    @Override
    public void write(List<Film> films) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Oscar Films");

        String[] columnTitles = {"YEAR", "FILM NAME", "DIRECTOR"};
        addColumnTitles(columnTitles, sheet);
        addFilmEntries(sheet, films);
        adjustColumnWidth(columnTitles, sheet);

        FileOutputStream outputStream = new FileOutputStream(EXCEL_FILE_PATH);
        workbook.write(outputStream);
        workbook.close();
    }

    private void addColumnTitles(String[] columnTitles, XSSFSheet sheet) {
        int rowNum = 0;
        int colNum = 0;
        Row titleRow = sheet.createRow(rowNum);
        CellStyle cellStyle = titleRow.getSheet().getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        for (String columnTitle : columnTitles
        ) {
            Cell yearCell = titleRow.createCell(colNum++);
            yearCell.setCellStyle(cellStyle);
            yearCell.setCellValue(columnTitle);
        }
    }

    private void addFilmEntries(XSSFSheet sheet, List<Film> films) {
        int rowNum = 1;
        for (Film film : films) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            Object[] filmFieldsArray = film.getObjectArrayFromFilmFields();
            for (Object field : filmFieldsArray) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(field.toString());
            }
        }
    }

    private void adjustColumnWidth(String[] columnTitles, XSSFSheet sheet) {
        for (int i = 0; i < columnTitles.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
