package springAiDemo.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springAiDemo.demo.services.AIService;

@RestController
@RequestMapping("/api/v1")
public class AIController {

    @Autowired
    AIService aiService;

    @GetMapping("/get-products")
    public Object getProducts(Integer page, String category){
        return aiService.getProducts(page, category);
    }
}