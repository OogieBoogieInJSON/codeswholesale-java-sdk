package resources;

import lombok.Getter;
import lombok.Setter;
import models.Price;

import java.util.List;

@Getter
@Setter
public class Product {
  private String productId;

  private String name;

  private String platform;

  private int quantity;

  private List<String> regions;

  private List<Price> prices;

  private String releaseDate;
}
