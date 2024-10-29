package daj.adapter.product.outDB;

import org.springframework.stereotype.Component;

import daj.product.port.in.dto.ProductModel;
import daj.adapter.product.outDB.entity.ProductEntity;
import daj.adapter.product.outDB.repository.ProductRepository;
import daj.adapter.product.utils.ProductMapper;
import daj.product.port.out.IProductWriterOutputPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductWriterDBAdapter implements IProductWriterOutputPort {

  private final ProductRepository repo;

  private final ProductMapper mapper;

  private final ProductReaderDbAdapter reader;

  @Override
  public ProductModel save(ProductModel input) {
    final ProductEntity casted = mapper.modelToEntity(input);
    final var saved = this.repo.save(casted);
    final var output = mapper.entityToModel(saved);
    return output;
  }

  @Override
  public ProductModel delete(Integer toDel) {
    reader.findByIdOrThrow(toDel);
    this.repo.deleteById(toDel);
    final var output = new ProductModel(1, null, null, null, null, null, null, null);
    return output;
  }

  @Override
  public ProductModel update(Integer id, ProductModel newInfo) {
    final var inDB = reader.findByIdOrThrow(id);
    final var merged = inDB.overwrite(newInfo);

    final ProductEntity updated = this.repo.save(merged);
    final ProductModel output = mapper.entityToModel(updated);
    return output;
  }

}
