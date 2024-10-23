package daj.adapter.product.outDB;

import org.springframework.stereotype.Component;

import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductSaveInfo;
import daj.adapter.product.inWeb.reqAndRes.ProductSimpleResponse;
import daj.adapter.product.utils.ProductMapper;
import daj.product.port.in.dto.ProductSimpleInfo;
import daj.product.port.out.IProductWriterOutputPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductWriterDBAdapter implements IProductWriterOutputPort {

  private final ProductRepository repo;

  private final ProductMapper productMapper;

  private final ProductReaderDbAdapter reader;

  @Override
  public ProductSimpleInfo save(ProductSaveInfo input) {
    final var casted = productMapper.saveRequestToEntity(input);
    final var saved = this.repo.save(casted);
    final var output = productMapper.entityToSimpleResponse(saved);
    return output;
  }

  @Override
  public ProductSimpleInfo delete(Integer toDel) {
    reader.findById(toDel);
    this.repo.deleteById(toDel);
    return new ProductSimpleResponse(toDel);
  }

  @Override
  public IProductAllPublicInfo update(Integer id, ProductSaveInfo newInfo) {
    final var inDB = reader.findById(id);
    final var merged = (ProductEntity)inDB.overwrite(newInfo);
    final IProductAllPublicInfo updated = this.repo.save(merged);
    return updated;
  }
  
  
}
