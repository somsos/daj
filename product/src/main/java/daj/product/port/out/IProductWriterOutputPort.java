package daj.product.port.out;

import daj.product.port.in.dto.ProductSimpleInfo;
import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductSaveInfo;

public interface IProductWriterOutputPort {
  
  public ProductSimpleInfo save(ProductSaveInfo input);

  public ProductSimpleInfo delete(Integer toDel);

  public IProductAllPublicInfo update(Integer id, ProductSaveInfo newInfo);

}
