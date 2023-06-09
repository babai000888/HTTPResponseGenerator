package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/api")
public class MessageController {

//    @GetMapping
//    public Response getPDF() {
//        Response response = new Response();
//        response.setStatus(1000);
//        response.setResponse(null);
//        return response;

    @GetMapping
    public String getPDF() {
        return "HELLO";
    }

}
