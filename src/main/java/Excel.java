import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Excel implements Readable, Writeable {

    static final String EXCEL_FILE_PATH = "C:\\Users\\Jakub\\IdeaProjects\\Films\\Output.xlsx";

    @Override
    public List read() throws IOException {
        List<Film> films = new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(new File(EXCEL_FILE_PATH));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();


        while (rowIterator.hasNext()) {
            int filmYear = 0;
            String filmName = "";
            String[] director;
            String directorName = "";
            String directorLastName = "";
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) {
                continue;
            }

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getColumnIndex()) {
                    case 0:
                        filmYear = Integer.parseInt(cell.getStringCellValue());
                        break;
                    case 1:
                        filmName = (cell.getStringCellValue());
                        break;
                    case 2:
                        director = cell.getStringCellValue().split(" ");
                        directorName = director[0];
                        for (int i = 1; i < director.length; i++) {
                            if (i != 1) {
                                directorLastName += " ";
                            }
                            directorLastName += director[i];
                        }
                        break;
                }
            }
            films.add(new Film(filmYear, filmName, directorName, directorLastName));
        }
        //System.out.println(films);
        workbook.close();
        inputStream.close();

        return films;
    }

    @Override
    public void write(List<Film> films) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Oscar Films");

        String[] columnTitles = {"YEAR", "FILM NAME", "DIRECTOR"};
        addColumnTitles(columnTitles, sheet);
        addFilmEntries(sheet, films);
        adjustColumnWidth(sheet);

        FileOutputStream outputStream = new FileOutputStream(EXCEL_FILE_PATH);
        workbook.write(outputStream);
        workbook.close();
    }

    private void addColumnTitles(String[] columnTitles, XSSFSheet sheet) {
        int rowNum = 0;
        int colNum = 0;
        Row titleRow = sheet.createRow(rowNum++);
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

    private void adjustColumnWidth(XSSFSheet sheet) {
        for (int i = 0; i < 3; i++)
            sheet.autoSizeColumn(i);
    }
}
