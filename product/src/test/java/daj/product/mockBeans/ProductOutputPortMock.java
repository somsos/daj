package daj.product.mockBeans;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import daj.product.port.in.dto.ProductModel;
import daj.product.port.out.IProductWriterOutputPort;

@Profile("test")
@Component
public class ProductOutputPortMock implements IProductWriterOutputPort {

  @Override
  public ProductModel save(ProductModel input) {
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public ProductModel delete(Integer toDel) {
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public ProductModel update(Integer id, ProductModel newInfo) {
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  
  
}
