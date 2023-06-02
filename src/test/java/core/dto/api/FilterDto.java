package core.dto.api;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterDto {

  private List<Condition> conditions;
  private String description;
  private String name;
  private List<Order> orders;
  private boolean share;
  private String type;
  private Integer id;

  @Getter
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Condition {
    private String condition;
    private String filteringField;
    private String value;
  }

  @Getter
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Order {
    private boolean isAsc;
    private String sortingColumn;
  }
}
