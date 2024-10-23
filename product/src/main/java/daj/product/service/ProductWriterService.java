package daj.product.service;

import org.springframework.stereotype.Service;

import daj.product.port.in.IProductWriteInputPort;
import daj.product.port.in.dto.ProductSimpleInfo;
import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductSaveInfo;
import daj.product.port.out.IProductWriterOutputPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductWriterService implements IProductWriteInputPort {

  private final IProductWriterOutputPort productOutP;

  @Override
  public ProductSimpleInfo save(ProductSaveInfo input) {
    final var saved = productOutP.save(input);
    return saved;
  }

  @Override
  public ProductSimpleInfo delete(Integer id) {
    final var deleted = productOutP.delete(id);
    return deleted;
  }

  @Override
  public IProductAllPublicInfo update(Integer id, ProductSaveInfo newInfo) {
    final var updated = productOutP.update(id, newInfo);
    return updated;
  }
  
}
