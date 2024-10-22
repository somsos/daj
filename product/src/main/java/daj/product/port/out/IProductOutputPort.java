package daj.product.port.out;

import daj.product.port.in.dto.ProductResponseInfo;
import daj.product.port.in.dto.ProductSaveInfo;

public interface IProductOutputPort {
  
  public ProductResponseInfo save(ProductSaveInfo input);

}
