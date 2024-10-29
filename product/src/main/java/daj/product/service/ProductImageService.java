package daj.product.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import daj.product.port.in.IProductImageInputPort;
import daj.product.port.in.dto.ProductImageModel;
import daj.product.port.out.IProductImageOutputPort;


@Service
@RequiredArgsConstructor
public class ProductImageService implements IProductImageInputPort {

  private final IProductImageOutputPort imageOP;

  @Override
  public ProductImageModel saveImage(ProductImageModel imageEntity) {
    final ProductImageModel saved = imageOP.saveImage(imageEntity);
    return saved;
  }
  

  @Override
  public ProductImageModel findImageById(Integer id) {
    final ProductImageModel found = imageOP.findImageById(id);
    return found;
  }

  public ProductImageModel delete(Integer id) {
    final ProductImageModel deleted = imageOP.delete(id);
    return deleted;
  }


}
