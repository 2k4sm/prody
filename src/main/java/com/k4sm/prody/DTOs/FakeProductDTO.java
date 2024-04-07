package com.k4sm.prody.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeProductDTO {
    int id;
    String title;
    String description;
    double price;
    String category;
    String image;
}