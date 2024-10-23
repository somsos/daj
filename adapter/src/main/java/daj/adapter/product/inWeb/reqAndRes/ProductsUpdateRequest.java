package daj.adapter.product.inWeb.reqAndRes;

import org.hibernate.validator.constraints.Range;

import daj.product.port.in.dto.ProductSaveInfo;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProductsUpdateRequest implements ProductSaveInfo {

  @Nullable
  @Size(min = 4, max = 120)
  private String name;
  
  @Nullable
  @Range(min = 10, max = 100000)
  private Float price;
  
  @Nullable
  @Range(min = 0, max = 100000)
  private Integer amount;
  
  @Nullable
  @Size(min = 4, max = 120)
  private String description;
  
}
