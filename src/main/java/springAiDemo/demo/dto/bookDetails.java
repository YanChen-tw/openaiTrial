package springAiDemo.demo.dto;

import java.util.Date;

public record bookDetails (
        String category,
        String book,
        String year,
        String review,
        String author,
        String summary
){};