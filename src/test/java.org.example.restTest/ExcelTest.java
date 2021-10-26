import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.io.IOUtils;
import org.example.restTest.controller.dto.TestRequestObject;
import org.example.restTest.service.ExcelService;
import org.example.restTest.service.OkHttpInvocationBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExcelTest {
    private ExcelService excelPOIHelper = new ExcelService();
    private static String FILE_NAME = "/excelTest.xlsx";
    private String fileLocation = "/Users/yujianzhou/Desktop";

    private ObjectMapper objectMapper = new ObjectMapper();
    private OkHttpClient okHttpClient = new OkHttpClient();


    String url = "http://localhost:8081/bodyvar2";
    HttpUrl httpUrl = HttpUrl.parse("http://localhost:8081/bodyvar2");
    OkHttpInvocationBuilder okhttpInvocationBuilder = new OkHttpInvocationBuilder(objectMapper,okHttpClient,httpUrl);


    @Test
    public void whenParsingPOIExcelFile_thenCorrect() throws Exception {
        fileLocation = fileLocation +FILE_NAME;
        Map<Integer, List<String>> data
                = excelPOIHelper.readExcel(fileLocation);
        //System.out.println(data);

        TestRequestObject object = new TestRequestObject(data.get(1).get(0),data.get(1).get(1),data.get(1).get(2));
        InputStream inputStream = okhttpInvocationBuilder.post(object);
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8);
        System.out.println(writer.toString());
    }
}
