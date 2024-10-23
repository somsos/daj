package daj.product.mockBeans;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.out.IProductReaderOutputPort;

@Profile("test")
@Component
public class ProductReaderOutputPort implements IProductReaderOutputPort {

  @Override
  public IProductAllPublicInfo findById(Integer input) {
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }
  
}