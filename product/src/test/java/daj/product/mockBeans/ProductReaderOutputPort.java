package daj.product.mockBeans;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductAllPublicInfo;
import daj.product.port.in.dto.RProductImage;
import daj.product.port.out.IProductReaderOutputPort;

@Profile("test")
@Component
public class ProductReaderOutputPort implements IProductReaderOutputPort {

  @Override
  public IProductAllPublicInfo findByIdOrThrow(Integer input) {
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public Page<ProductAllPublicInfo> findByPage(int page, int size) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getProductsByPage'");
  }

  @Override
  public RProductImage findImageByName(Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findImageByName'");
  }
  
}
