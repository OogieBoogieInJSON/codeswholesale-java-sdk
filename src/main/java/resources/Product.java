package resources;

import lombok.Getter;
import lombok.Setter;
import models.Price;

import java.util.List;

@Getter
@Setter
public class Product {
  /**
   * Product identifier
   */
  private String productId;

  /**
   * Product name
   */
  private String name;

  /**
   * Product's platform
   */
  private String platform;

  /**
   * Current quantity on stock
   */
  private int quantity;

  /**
   * Game's covers
   */
  private List<ProductImage> images;

  /**
   * Available regions
   */
  private List<String> regions;


  /**
   * Product's prices
   */
  private List<Price> prices;


  /**
   * Product's release date
   */
  private String releaseDate;
}
