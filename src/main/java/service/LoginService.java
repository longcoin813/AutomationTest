package service;

import helper.ExcelLoader;
import model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginService {
    public List<User> getList() {
        List<User> list = new ArrayList<User>();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir") + "\\excel\\testdata.xlsx"));
            Sheet sheet = new ExcelLoader().getSheet(fileInputStream, 0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Iterator<Cell> cellIterator = rowIterator.next().iterator();
                cellIterator.next();
                list.add(new User()
                        .setUsername(cellIterator.next().getStringCellValue())
                        .setPassword(cellIterator.next().getStringCellValue())
                );
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
