package daj.product.port.in;

import daj.product.port.in.dto.ProductModel;

public interface IProductWriteInputPort {

  ProductModel save(ProductModel input);

  ProductModel delete(Integer id);

  ProductModel update(Integer id, ProductModel newInfo);

}
