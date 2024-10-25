package daj.adapter.product.inWeb.reqAndRes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import daj.product.port.in.dto.ProductSimpleInfo;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductSimpleResponse implements ProductSimpleInfo {

  public Integer id;

  @Nullable
  @JsonInclude(Include.NON_NULL)
  public String message;

}
