package daj.adapter.product.outDB;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import daj.product.port.in.dto.ProductModel;
import daj.product.port.in.dto.ProductImageModel;

import org.springframework.stereotype.Component;

import daj.adapter.product.outDB.entity.ProductEntity;
import daj.adapter.product.outDB.entity.ProductImageEntity;
import daj.adapter.product.outDB.repository.ImageProductRepository;
import daj.adapter.product.outDB.repository.ProductRepository;
import daj.adapter.product.utils.ProductConstants;
import daj.adapter.product.utils.ProductImageMapper;
import daj.adapter.product.utils.ProductMapper;
import daj.common.error.ErrorResponse;
import daj.product.port.out.IProductReaderOutputPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductReaderDbAdapter implements IProductReaderOutputPort {

  private final ProductRepository repo;

  private final ProductMapper mapper;

  private final ProductImageMapper imageMapper;

  private final ImageProductRepository imageRepo;

  
  public ProductEntity findByIdOrThrow(Integer id) {
    var found = this.repo.findById(id).orElse(null);
    if(found == null) {
      throw new ErrorResponse(ProductConstants.NOT_FOUND, 404, "not_found");
    }
    return found;
  }

  @Override
  public ProductModel findDetailsById(Integer id) {
    var found = this.findByIdOrThrow(id);
    return found;
  }

  @Override
  public Page<ProductModel> findByPage(int page, int size) {
    final var pageFound = repo.findAll(PageRequest.of(page, size));
    final List<ProductModel> contentMapped = pageFound.getContent().stream().map(e -> { return mapper.entityToModel(e); }).toList();
    final var pageMapped = new PageImpl<>(contentMapped, pageFound.getPageable(), pageFound.getSize());
    return pageMapped;
  }

  @Override
  public ProductImageModel findImageByName(Integer id) {
    final ProductImageEntity found = imageRepo.findById(id).orElse(null);
    final ProductImageModel output = imageMapper.entityToModel(found);
    return output;
  }
  
}
