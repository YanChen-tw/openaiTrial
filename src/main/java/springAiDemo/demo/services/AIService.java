package springAiDemo.demo.services;

import org.springframework.ai.client.AiClient;
import org.springframework.ai.client.AiResponse;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springAiDemo.demo.dto.ProductDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class AIService {

    @Autowired
    AiClient aiClient;

    private List<ProductDetails> generateRandomProducts(int numProducts) {
        List<ProductDetails> products = new ArrayList<>();
        String[] categories = {"Clothing", "Electronics", "Kitchen", "Home", "Office"};
        Random random = new Random();

        for (int i = 0; i < numProducts; i++) {
            String randomName = "Product " + (i + 1);
            double randomPrice = Math.round(random.nextDouble() * 1000) / 10.0; // Up to 2 decimal places
            String randomCategory = categories[random.nextInt(categories.length)];
            String image = "path/to/placeholder_image.jpg"; // Placeholder image path
            int randomInventory = random.nextInt(100) + 1; // Between 1 and 100

            products.add(new ProductDetails(randomName, randomPrice, randomCategory, image, randomInventory));
        }
        return products;
    }

    public Object getProducts(Integer page, String category){
        List<ProductDetails> originalProducts = generateRandomProducts(100);

        PromptTemplate promptTemplate = new PromptTemplate("""
            Please provide a product list filter from {originalProducts} for the given {category} and {page} which split with 30 items per page.
            Please provide the product details in the JSON format
            """);
        promptTemplate.add("originalProducts", originalProducts);
        promptTemplate.add("page", page);
        promptTemplate.add("category", category);
        AiResponse response = aiClient.generate(promptTemplate.create());

        return response.getGeneration().getText();
    }
}