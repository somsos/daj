package daj.adapter.product.outDB;

import org.springframework.stereotype.Component;

import daj.product.port.in.dto.ProductSaveInfo;
import daj.adapter.product.inWeb.reqAndRes.ProductSimpleResponse;
import daj.adapter.product.utils.ProductConstants;
import daj.adapter.product.utils.ProductMapper;
import daj.common.error.ErrorResponse;
import daj.product.port.in.dto.ProductSimpleInfo;
import daj.product.port.out.IProductWriterOutputPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductWriterDBAdapter implements IProductWriterOutputPort {

  private final ProductRepository repo;

  private final ProductMapper productMapper;

  @Override
  public ProductSimpleInfo save(ProductSaveInfo input) {
    final var casted = productMapper.saveRequestToEntity(input);
    final var saved = this.repo.save(casted);
    final var output = productMapper.entityToSimpleResponse(saved);
    return output;
  }

  @Override
  public ProductSimpleInfo delete(Integer toDel) {
    var found = this.repo.findById(toDel).orElse(null);
    if(found == null) {
      throw new ErrorResponse(ProductConstants.NOT_FOUND, 404, "not_found");
    }
    this.repo.deleteById(toDel);
    return new ProductSimpleResponse(toDel);
  }
  
}
