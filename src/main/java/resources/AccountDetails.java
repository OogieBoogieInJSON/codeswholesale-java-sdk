package resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDetails {
  /**
   * Account full name
   */
  private String fullName;

  /**
   * Account email
   */
  private String email;

  /**
   * Balance on CodesWholesale
   */
  private Float currentBalance;

  /**
   * Credit on CodesWholesale
   */
  private Float currentCredit;

  /**
   * Credit + balance
   */
  private Float totalToUse;
}
