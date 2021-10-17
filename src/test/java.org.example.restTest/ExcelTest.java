import org.example.restTest.service.ExcelService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExcelTest {
    private ExcelService excelPOIHelper = new ExcelService();
    private static String FILE_NAME = "/excelTest.xlsx";
    private String fileLocation = "/Users/yujianzhou/Desktop";

    @Test
    public void whenParsingPOIExcelFile_thenCorrect() throws Exception {
        fileLocation = fileLocation +FILE_NAME;
        Map<Integer, List<String>> data
                = excelPOIHelper.readExcel(fileLocation);
        System.out.println(data);
        assertEquals("muyidalao", data.get(1).get(0));
        assertEquals("13.0", data.get(1).get(1));
        assertEquals("www.google.com", data.get(1).get(2));
    }
}
