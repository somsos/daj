package daj.product.port.out;

import daj.product.port.in.dto.ProductSimpleInfo;
import daj.product.port.in.dto.ProductSaveInfo;

public interface IProductWriterOutputPort {
  
  public ProductSimpleInfo save(ProductSaveInfo input);

  public ProductSimpleInfo delete(Integer toDel);

}
