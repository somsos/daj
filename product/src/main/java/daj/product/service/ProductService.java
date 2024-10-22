package daj.product.service;

import org.springframework.stereotype.Service;

import daj.product.port.in.IProductInputPort;
import daj.product.port.in.dto.ProductResponseInfo;
import daj.product.port.in.dto.ProductSaveInfo;
import daj.product.port.out.IProductOutputPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductInputPort {

  private final IProductOutputPort productOutP;

  @Override
  public ProductResponseInfo save(ProductSaveInfo input) {
    final var saved = productOutP.save(input);
    return saved;
  }
  
}
