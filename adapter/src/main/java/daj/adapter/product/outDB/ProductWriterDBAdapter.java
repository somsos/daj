package daj.adapter.product.outDB;

import org.springframework.stereotype.Component;

import daj.product.port.in.dto.ProductModel;
import daj.adapter.product.outDB.entity.ProductEntity;
import daj.adapter.product.outDB.entity.ProductImageEntity;
import daj.adapter.product.outDB.repository.ImageProductRepository;
import daj.adapter.product.outDB.repository.ProductRepository;
import daj.adapter.product.utils.ProductImageMapper;
import daj.adapter.product.utils.ProductMapper;
import daj.product.port.in.dto.ProductImageModel;
import daj.product.port.out.IProductWriterOutputPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductWriterDBAdapter implements IProductWriterOutputPort {

  private final ProductRepository repo;

  private final ImageProductRepository imageRepo;

  private final ProductMapper mapper;

  private final ProductImageMapper imageMapper;

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
    final var output = new ProductModel(1, null, null, null, null, null, null);
    return output;
  }

  @Override
  public ProductModel update(Integer id, ProductModel newInfo) {
    final var inDB = reader.findByIdOrThrow(id);
    final var merged = inDB.overwrite(newInfo);
    final ProductModel updated = this.repo.save(merged);
    return updated;
  }

  @Override
  public ProductImageModel saveImage(ProductImageModel rProductImage) {
    //Check if exists
    final var productInDB = reader.findByIdOrThrow(rProductImage.getProduct().getId());

    // save
    rProductImage.setProduct(productInDB);

    final ProductImageEntity toSave = imageMapper.modelToEntity(rProductImage);
    final ProductImageEntity saved = imageRepo.save(toSave);
    final ProductImageModel output = imageMapper.entityToModel(saved);
    return output;
  }
  
  
}
