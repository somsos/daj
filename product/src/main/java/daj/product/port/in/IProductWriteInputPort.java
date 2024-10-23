package daj.product.port.in;

import daj.product.port.in.dto.ProductSimpleInfo;
import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductSaveInfo;

public interface IProductWriteInputPort {

  ProductSimpleInfo save(ProductSaveInfo input);

  ProductSimpleInfo delete(Integer id);

  IProductAllPublicInfo update(Integer id, ProductSaveInfo newInfo);

}
