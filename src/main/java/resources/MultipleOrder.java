package resources;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class MultipleOrder {
  /**
   * Id of ordered product
   */
  private String productId;

  /**
   * A collection of ordered items
   */
  private Collection<Order> items;
}
