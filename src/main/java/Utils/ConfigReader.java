package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ConfigReader {

    public static final long PAGE_LOAD_TIMEOUT = 20;
    public static final long IMPLICIT_WAIT = 20;

    // Path matches your current project structure
    public static final String TESTDATA_SHEET_PATH = System.getProperty("user.dir")
            + File.separator + "src"
            + File.separator + "main"
            + File.separator + "java"
            + File.separator + "testdata"
            + File.separator + "LoginData.xlsx";

    static XSSFWorkbook book;
    static XSSFSheet sheet;
    static XSSFCell cell;
    static XSSFRow row;

    public static Object[][] getTestData(String sheetName) throws IOException {

        FileInputStream file = new FileInputStream(TESTDATA_SHEET_PATH);

        book = new XSSFWorkbook(file);
        sheet = book.getSheet(sheetName);

        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i + 1);
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                cell = row.getCell(k);
                data[i][k] = (cell != null) ? cell.toString() : "";
            }
        }
        file.close();
        return data;
    }
}
