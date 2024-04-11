package com.k4sm.prody.dtos;

import com.k4sm.prody.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RealProductDTO {
  int id;
  String title;
  String description;
  double price;
  String image;
  Category category;
}
