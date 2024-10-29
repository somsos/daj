package daj.product.port.in;

import daj.product.port.in.dto.ProductImageModel;

public interface IProductImageInputPort {

  ProductImageModel saveImage(ProductImageModel imageEntity);

  ProductImageModel findImageById(Integer id);

  ProductImageModel delete(Integer id);

}
