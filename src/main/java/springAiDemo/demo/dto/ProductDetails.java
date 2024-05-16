package springAiDemo.demo.dto;

public record ProductDetails(
    String name,
    Double price,
    String category,
    String image,
    Integer inventory
){};
