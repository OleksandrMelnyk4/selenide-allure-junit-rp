package core.dto.api;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltersDto {

  private String owner;
  private boolean share;
  private Integer id;
  private String name;
  private ArrayList<Condition> conditions;
  private ArrayList<Order> orders;
  private String type;
  private String description;

  @Getter
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Condition {
    private String filteringField;
    private String condition;
    private String value;
  }

  @Getter
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Order {
    private String sortingColumn;
    private boolean isAsc;
  }
}



