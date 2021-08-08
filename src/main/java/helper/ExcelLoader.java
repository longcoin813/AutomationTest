package helper;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

public class ExcelLoader {
    public Sheet getSheet(InputStream inputStream, int sheetIndex) throws IOException {
        return new XSSFWorkbook(inputStream).getSheetAt(sheetIndex);
    }
}