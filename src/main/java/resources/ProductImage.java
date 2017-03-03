package resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImage {
  /**
   * Image URL
   */
  private String image;

  /**
   * Size type of the image. Can be SMALL, MEDIUM
   */
  private String format;
}
