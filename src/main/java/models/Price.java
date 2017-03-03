package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * The prices are defined in ranges. For example, between 1 and 100 purchased code keys it might cost X and between 100
 * and 200 it will cost Y
 */
public class Price {
  /**
   * Code key price
   */
  private Float value;

  /**
   * Left limit
   */
  private Float from;

  /**
   * Right limit
   */
  private Float to;
}
