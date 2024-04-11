package com.k4sm.prody.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RealCreateProductDTO {
    String title;
    String description;
    double price;
    String image;
    String category;
}
