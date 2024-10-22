package daj.adapter.product.inWeb.reqAndRes;

import java.util.Date;

import daj.product.port.in.dto.ProductResponseInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductSimpleResponse implements ProductResponseInfo {

  public Integer id;

  public Date createdAt;

}
