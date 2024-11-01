package daj.product.internal.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import daj.product.visible.port.dto.ProductDto;
import daj.product.visible.port.in.IProductReadInputPort;
import daj.product.visible.port.out.IProductReaderOutputPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductReaderService implements IProductReadInputPort {

  final private IProductReaderOutputPort readerDB;

  @Override
  public ProductDto findDetailsById(Integer id) {
    final var found = readerDB.findDetailsById(id);
    return found;
  }

  @Override
  public Page<ProductDto> findByPage(int page, int size) {
    final var pageFound = readerDB.findByPage(page, size);
    return pageFound;
  }

  
  
}
