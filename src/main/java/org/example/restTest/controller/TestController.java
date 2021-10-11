package org.example.restTest.controller;

//import org.example.restTest.controller.dto.customResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
//@RequestMapping(path="/yujiandalao")
public class TestController {

    @GetMapping("/jiqiang")
    public ResponseEntity<String> listReport() {

        return ResponseEntity.ok("shangfen");
    }

    //get http://localhost:8080/requestParam?abc=10
    @GetMapping(value = "/requestParam")
    public ResponseEntity<String> listReport(@RequestParam(name = "abc") String a) {
        System.out.println(a);
        return ResponseEntity.ok(a);
    }

    //get http://localhost:8080/bodyvar
    //body:
    //    {
    //        "a":10,
    //            "b":"adsfasd"
    //    }
    @GetMapping(value = "/bodyvar")
    public ResponseEntity<customResponse> listReport(@RequestBody customResponse object) {
        System.out.println(object);
        return ResponseEntity.ok(object);
    }

    //get http://localhost:8080/pathvar/10
    @GetMapping("/pathvar/{a}")
    public ResponseEntity<String> bodyvar(@PathVariable(required = false) String a) {
        System.out.println(a);
        return ResponseEntity.ok(a);
    }

    @GetMapping("/jiqiang1")
    public void listReport(HttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.setHeader("Date", "asdfasf");
        response.getWriter().append("yujiandalaojiqiang");
    }

    @GetMapping("/jiqiang3")
    public customResponse api() throws IOException {
        return new customResponse();
    }

    static class customResponse{
        int a = 10;
        String b = "yujiandalaojiqiang";
        public int getA(){
            return a;
        }
        public String getB(){
            return b;
        }
        @Override
        public String toString(){
            return a + " " + b;
        }
    }

}




