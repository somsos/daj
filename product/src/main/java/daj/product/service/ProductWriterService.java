package daj.product.service;

import org.springframework.stereotype.Service;

import daj.product.port.in.IProductWriteInputPort;
import daj.product.port.in.dto.ProductModel;
import daj.product.port.in.dto.ProductImageModel;
import daj.product.port.out.IProductWriterOutputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductWriterService implements IProductWriteInputPort {

  private final IProductWriterOutputPort productOutP;

  @Override
  public ProductModel save(@Valid ProductModel input) {
    final var saved = productOutP.save(input);
    return saved;
  }

  @Override
  public ProductModel delete(Integer id) {
    final var deleted = productOutP.delete(id);
    return deleted;
  }

  @Override
  public ProductModel update(Integer id, ProductModel newInfo) {
    final var updated = productOutP.update(id, newInfo);
    return updated;
  }

  @Override
  public ProductImageModel saveImage(ProductImageModel imageEntity) {
    final ProductImageModel saved = productOutP.saveImage(imageEntity);
    return saved;
  }
  
}
