package daj.product.mockBeans;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import daj.product.visible.port.dto.ProductDto;
import daj.product.visible.port.out.IProductReaderOutputPort;

@Profile("test")
@Component
public class ProductReaderOutputPort implements IProductReaderOutputPort {

  @Override
  public ProductDto findDetailsById(Integer input) {
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public Page<ProductDto> findByPage(int page, int size) {
    throw new UnsupportedOperationException("Unimplemented method 'getProductsByPage'");
  }

  
  
}
