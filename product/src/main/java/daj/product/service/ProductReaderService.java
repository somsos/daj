package daj.product.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import daj.product.port.in.IProductReadInputPort;
import daj.product.port.in.dto.ProductModel;
import daj.product.port.in.dto.ProductImageModel;
import daj.product.port.out.IProductReaderOutputPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductReaderService implements IProductReadInputPort {

  final private IProductReaderOutputPort readerDB;

  @Override
  public ProductModel findDetailsById(Integer id) {
    final var found = readerDB.findDetailsById(id);
    return found;
  }

  @Override
  public Page<ProductModel> getProductsByPage(int page, int size) {
    final var pageFound = readerDB.findByPage(page, size);
    return pageFound;
  }

  @Override
  public ProductImageModel findImageByName(Integer id) {
    final ProductImageModel found = readerDB.findImageByName(id);
    return found;
  }
  
}
