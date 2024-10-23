package daj.adapter.product.outDB;

import org.springframework.stereotype.Component;

import daj.adapter.product.utils.ProductConstants;
import daj.common.error.ErrorResponse;
import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.out.IProductReaderOutputPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductReaderDbAdapter implements IProductReaderOutputPort {

  private final ProductRepository repo;

  @Override
  public IProductAllPublicInfo findById(Integer id) {
    var found = this.repo.findById(id).orElse(null);
    if(found == null) {
      throw new ErrorResponse(ProductConstants.NOT_FOUND, 404, "not_found");
    }
    return found;
  }
  
}
