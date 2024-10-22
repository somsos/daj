package daj.adapter.product.inWeb.reqAndRes;

import daj.product.port.in.dto.ProductSimpleInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductSimpleResponse implements ProductSimpleInfo {

  public Integer id;

}
