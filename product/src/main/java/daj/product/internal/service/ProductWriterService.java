package daj.product.internal.service;

import org.springframework.stereotype.Service;

import daj.product.visible.port.dto.ProductDto;
import daj.product.visible.port.in.IProductWriteInputPort;
import daj.product.visible.port.out.IProductWriterOutputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductWriterService implements IProductWriteInputPort {

  private final IProductWriterOutputPort productOutP;

  @Override
  public ProductDto save(@Valid ProductDto input) {
    final var saved = productOutP.save(input);
    return saved;
  }

  @Override
  public ProductDto delete(Integer id) {
    final var deleted = productOutP.delete(id);
    return deleted;
  }

  @Override
  public ProductDto update(Integer id, ProductDto newInfo) {
    final var updated = productOutP.update(id, newInfo);
    return updated;
  }

  
  
}
