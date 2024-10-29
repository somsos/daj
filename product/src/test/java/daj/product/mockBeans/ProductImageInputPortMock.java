package daj.product.mockBeans;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import daj.product.port.in.dto.ProductImageModel;
import daj.product.port.out.IProductImageOutputPort;

@Profile("test")
@Component
public class ProductImageInputPortMock implements IProductImageOutputPort {

  @Override
  public ProductImageModel saveImage(ProductImageModel imageEntity) {
    throw new UnsupportedOperationException("Unimplemented method 'saveImage'");
  }

  @Override
  public ProductImageModel findImageById(Integer id) {
    throw new UnsupportedOperationException("Unimplemented method 'findImageByName'");
  }

  @Override
  public ProductImageModel delete(Integer id) {
    throw new UnsupportedOperationException("Unimplemented method 'findImageByName'");
  }
  
}
