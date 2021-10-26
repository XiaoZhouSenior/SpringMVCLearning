package org.example.restTest.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelService {
    // https://www.baeldung.com/java-microsoft-excel
    public Workbook getWorkBook(String path) throws Exception {
        FileInputStream file = new FileInputStream(new File(path));
        Workbook workbook = new XSSFWorkbook(file);
        return workbook;
    }

    public Map<Integer, List<String>> readExcel(String path) throws Exception {
        Workbook workbook = getWorkBook(path);
        Sheet sheet = workbook.getSheetAt(0);
        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (int j = 0; j < row.getLastCellNum();j++) {
                Cell cell = row.getCell(j);
                if(cell == null){
                    data.get(i).add("Empty");
                }else {
                    switch (cell.getCellTypeEnum()) {
                        case STRING:
                            data.get(i).add(cell.getRichStringCellValue().getString());
                            break;
                        case NUMERIC:
                            data.get(i).add(cell.getNumericCellValue() + "");
                            break;
                        case BOOLEAN:
                            data.get(i).add(cell.getBooleanCellValue() + "");
                            break;
                        default:
                            data.get(i).add("Empty");
                    }
                }
            }
            i++;
        }
        return data;
    }





}
