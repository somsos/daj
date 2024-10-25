package daj.adapter.product.outDB;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import daj.product.port.in.dto.ProductAllPublicInfo;
import daj.product.port.in.dto.RProductImage;

import org.springframework.stereotype.Component;

import daj.adapter.product.outDB.entity.ProductImageEntity;
import daj.adapter.product.outDB.repository.ImageProductRepository;
import daj.adapter.product.outDB.repository.ProductRepository;
import daj.adapter.product.utils.ProductConstants;
import daj.adapter.product.utils.ProductMapper;
import daj.common.error.ErrorResponse;
import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.out.IProductReaderOutputPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductReaderDbAdapter implements IProductReaderOutputPort {

  private final ProductRepository repo;

  private final ProductMapper mapper;

  private final ImageProductRepository imageRepo;

  @Override
  public IProductAllPublicInfo findByIdOrThrow(Integer id) {
    var found = this.repo.findById(id).orElse(null);
    if(found == null) {
      throw new ErrorResponse(ProductConstants.NOT_FOUND, 404, "not_found");
    }
    return found;
  }

  @Override
  public Page<ProductAllPublicInfo> findByPage(int page, int size) {
    final var pageFound = repo.findAll(PageRequest.of(page, size));
    final var contentMapped = mapper.entitiesToAllPublicInfos(pageFound.getContent());
    final var pageMapped = new PageImpl<>(contentMapped, pageFound.getPageable(), pageFound.getSize());
    return pageMapped;
  }

  @Override
  public RProductImage findImageByName(Integer id) {
    final ProductImageEntity found = imageRepo.findById(id).orElse(null);
    final RProductImage output = mapper.entityToModel(found);
    return output;
  }
  
}
