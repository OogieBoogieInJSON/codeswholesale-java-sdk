package resources;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
/**
 * Response model for all products API endpoint
 */
public class Products {
  private Collection<Product> items;
}
