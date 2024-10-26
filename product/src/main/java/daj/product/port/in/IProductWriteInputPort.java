package daj.product.port.in;

import daj.product.port.in.dto.ProductModel;
import daj.product.port.in.dto.RProductImage;

public interface IProductWriteInputPort {

  ProductModel save(ProductModel input);

  ProductModel delete(Integer id);

  ProductModel update(Integer id, ProductModel newInfo);

  RProductImage saveImage(RProductImage imageEntity);

}
