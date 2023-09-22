package com.CRUDOperation.Project.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {


    @NotEmpty
    private String author;

    @NotEmpty
    private String name;

    @Min(value = 0, message = "Price Should be Positive")
    private Integer price;
}
