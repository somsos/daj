package daj.product.mockBeans;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import daj.product.port.in.dto.ProductSimpleInfo;
import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductSaveInfo;
import daj.product.port.out.IProductWriterOutputPort;

@Profile("test")
@Component
public class ProductOutputPortMock implements IProductWriterOutputPort {

  @Override
  public ProductSimpleInfo save(ProductSaveInfo input) {
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public ProductSimpleInfo delete(Integer toDel) {
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public IProductAllPublicInfo update(Integer id, ProductSaveInfo newInfo) {
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }
  
}
