package daj.product.port.out;

import daj.product.port.in.dto.ProductImageModel;

public interface IProductImageOutputPort {

  ProductImageModel saveImage(ProductImageModel imageEntity);

  ProductImageModel findImageById(Integer id);

  ProductImageModel delete(Integer id);

  
}
