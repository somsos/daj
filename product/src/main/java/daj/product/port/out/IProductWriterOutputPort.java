package daj.product.port.out;

import daj.product.port.in.dto.ProductModel;

public interface IProductWriterOutputPort {
  
  public ProductModel save(ProductModel input);

  public ProductModel delete(Integer toDel);

  public ProductModel update(Integer id, ProductModel newInfo);

}
