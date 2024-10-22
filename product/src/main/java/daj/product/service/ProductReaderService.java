package daj.product.service;

import org.springframework.stereotype.Service;

import daj.product.port.in.IProductReadInputPort;
import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.out.IProductReaderOutputPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductReaderService implements IProductReadInputPort {

  final private IProductReaderOutputPort reader;

  @Override
  public IProductAllPublicInfo getById(Integer id) {
    final var found = reader.findById(id);
    return found;
  }
  
}
