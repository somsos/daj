package daj.adapter.product.inWeb.reqAndRes;

import org.hibernate.validator.constraints.Range;

import daj.product.port.in.dto.ProductSaveInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProductsSaveRequest implements ProductSaveInfo {

  @NotBlank
  @Size(min = 4, max = 120)
  private String name;
  
  @Range(min = 10, max = 100000)
  private Float price;
  
  @Range(min = 0, max = 100000)
  private Integer amount;
  
  @Size(min = 4, max = 120)
  private String description;
  
}
