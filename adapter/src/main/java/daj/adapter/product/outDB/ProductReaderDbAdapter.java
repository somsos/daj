package daj.adapter.product.outDB;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import daj.product.port.in.dto.ProductAllPublicInfo;
import org.springframework.stereotype.Component;

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

  @Override
  public IProductAllPublicInfo findById(Integer id) {
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
  
}
