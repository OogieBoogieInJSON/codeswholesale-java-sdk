package resources;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class MultipleOrder {
  private String productId;
  private Collection<Order> items;
}
