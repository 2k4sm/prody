package com.k4sm.prody.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    int id;
    String title;
    String description;
    double price;
    String image;
    Category category;
}
