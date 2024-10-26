package daj.product.mockBeans;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import daj.product.port.in.dto.ProductModel;
import daj.product.port.in.dto.ProductImageModel;
import daj.product.port.out.IProductReaderOutputPort;

@Profile("test")
@Component
public class ProductReaderOutputPort implements IProductReaderOutputPort {

  @Override
  public ProductModel findDetailsById(Integer input) {
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public Page<ProductModel> findByPage(int page, int size) {
    throw new UnsupportedOperationException("Unimplemented method 'getProductsByPage'");
  }

  @Override
  public ProductImageModel findImageByName(Integer id) {
    throw new UnsupportedOperationException("Unimplemented method 'findImageByName'");
  }
  
}
