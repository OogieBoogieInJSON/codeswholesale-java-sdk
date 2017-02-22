package resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDetails {
  private String fullName;
  private String email;
  private Float currentBalance;
  private Float currentCredit;
  private Float totalToUse;
}
