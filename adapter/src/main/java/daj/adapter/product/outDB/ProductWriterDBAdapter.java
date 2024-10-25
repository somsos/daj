package daj.adapter.product.outDB;

import org.springframework.stereotype.Component;

import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductSaveInfo;
import daj.adapter.product.inWeb.reqAndRes.ProductSimpleResponse;
import daj.adapter.product.outDB.entity.ProductEntity;
import daj.adapter.product.outDB.entity.ProductImageEntity;
import daj.adapter.product.outDB.repository.ImageProductRepository;
import daj.adapter.product.outDB.repository.ProductRepository;
import daj.adapter.product.utils.ProductMapper;
import daj.product.port.in.dto.ProductSimpleInfo;
import daj.product.port.in.dto.RProductImage;
import daj.product.port.out.IProductWriterOutputPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductWriterDBAdapter implements IProductWriterOutputPort {

  private final ProductRepository repo;

  private final ImageProductRepository imageRepo;

  private final ProductMapper mapper;

  private final ProductReaderDbAdapter reader;

  @Override
  public ProductSimpleInfo save(ProductSaveInfo input) {
    final var casted = mapper.saveRequestToEntity(input);
    final var saved = this.repo.save(casted);
    final var output = mapper.entityToSimpleResponse(saved);
    return output;
  }

  @Override
  public ProductSimpleInfo delete(Integer toDel) {
    reader.findByIdOrThrow(toDel);
    this.repo.deleteById(toDel);
    return new ProductSimpleResponse(toDel, null);
  }

  @Override
  public IProductAllPublicInfo update(Integer id, ProductSaveInfo newInfo) {
    final var inDB = reader.findByIdOrThrow(id);
    final var merged = (ProductEntity)inDB.overwrite(newInfo);
    final IProductAllPublicInfo updated = this.repo.save(merged);
    return updated;
  }

  @Override
  public RProductImage saveImage(RProductImage rProductImage) {
    //Check if exists
    final var productInDB = reader.findByIdOrThrow(rProductImage.getProduct().getId());

    // save
    final ProductImageEntity mapped = mapper.RProductImageToEntity(rProductImage);
    mapped.setProduct(productInDB);
    final ProductImageEntity saved = imageRepo.save(mapped);
    final RProductImage output = mapper.entityToModel(saved);
    return output;
  }
  
  
}
