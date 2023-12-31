package com.CRUDOperation.Project.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Books", schema = "voltsit7")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    private String author;

    @NotEmpty
    private String name;

    @Min(value = 0, message = "Price Should be Positive")
    private Integer price;

}
