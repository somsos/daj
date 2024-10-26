package daj.product.port.out;

import daj.product.port.in.dto.ProductModel;
import daj.product.port.in.dto.ProductImageModel;

public interface IProductWriterOutputPort {
  
  public ProductModel save(ProductModel input);

  public ProductModel delete(Integer toDel);

  public ProductModel update(Integer id, ProductModel newInfo);

  public ProductImageModel saveImage(ProductImageModel imageEntity);

}
