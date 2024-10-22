package daj.adapter.product.outDB;

import org.springframework.stereotype.Component;

import daj.product.port.in.dto.ProductSaveInfo;
import daj.adapter.product.utils.ProductMapper;
import daj.product.port.in.dto.ProductResponseInfo;
import daj.product.port.out.IProductOutputPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductDBAdapter implements IProductOutputPort {

  private final ProductRepository repo;

  private final ProductMapper productMapper;

  @Override
  public ProductResponseInfo save(ProductSaveInfo input) {
    final var casted = productMapper.saveRequestToEntity(input);
    final var saved = this.repo.save(casted);
    final var output = productMapper.entityToSimpleResponse(saved);
    return output;
  }
  
}
