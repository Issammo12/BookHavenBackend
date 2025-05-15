package org.example.pfa.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.converters.LongConverter;

import java.util.Date;
@Getter
@Setter

public class BookDTO {
    @CsvBindByName(column = "isbn13")
    private String isbn13;
    @CsvBindByName(column = "isbn10")
    private String isbn10;
    @CsvBindByName(column = "title")
    private String title;
    @CsvBindByName(column = "author")
    private String author;
    @CsvBindByName(column = "description")
    private String description;
    @CsvBindByName(column = "category")
    private String category;
    @CsvBindByName(column = "thumbnail")
    private String thumbnail;
    @CsvBindByName(column = "date_published")
    private Date datePublished;
    @CsvBindByName(column = "number_of_pages")
    private int numberOfPages;
}
