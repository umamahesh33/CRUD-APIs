package com.example.JBDL.databaseconnection;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Book{
    private int id;
    private String bookName;
    private String authorName;
    private int cost;
}
