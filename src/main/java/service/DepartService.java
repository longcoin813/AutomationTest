package service;

import helper.ExcelLoader;
import model.Departs;
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

public class DepartService {
    public List<Departs> getList() {
        List<Departs> list = new ArrayList<Departs>();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir") + "\\excel\\testdata.xlsx"));
            Sheet sheet = new ExcelLoader().getSheet(fileInputStream, 2);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Iterator<Cell> cellIterator = rowIterator.next().iterator();
                cellIterator.next();
                list.add(new Departs()
                        .setDepartid(cellIterator.next().getStringCellValue())
                        .setDepartname(cellIterator.next().getStringCellValue())
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
