package com.k4sm.prody.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    int id;
    String name;
    String description;
    double price;
    String image;
    Catagory category;
}
