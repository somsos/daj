package daj.product.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import daj.product.port.in.IProductReadInputPort;
import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductAllPublicInfo;
import daj.product.port.in.dto.RProductImage;
import daj.product.port.out.IProductReaderOutputPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductReaderService implements IProductReadInputPort {

  final private IProductReaderOutputPort readerDB;

  @Override
  public IProductAllPublicInfo getById(Integer id) {
    final var found = readerDB.findByIdOrThrow(id);
    return found;
  }

  @Override
  public Page<ProductAllPublicInfo> getProductsByPage(int page, int size) {
    final var pageFound = readerDB.findByPage(page, size);
    return pageFound;
  }

  @Override
  public RProductImage findImageByName(Integer id) {
    final RProductImage found = readerDB.findImageByName(id);
    return found;
  }
  
}
