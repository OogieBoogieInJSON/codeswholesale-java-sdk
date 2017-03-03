package resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
  /**
   * Type of code. Can be CODE_TEXT, CODE_IMAGE and CODE_PREORDER
   */
  private String codeType;

  /**
   * Game key
   */
  private String code;

  /**
   * Not documented in API
   */
  private String filename;
}
