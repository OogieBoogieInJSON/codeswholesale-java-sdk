package resources;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class ProductsResponse {
  private Collection<Product> items;
}
