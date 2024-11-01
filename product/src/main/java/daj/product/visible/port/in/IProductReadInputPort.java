package daj.product.visible.port.in;

import org.springframework.data.domain.Page;

import daj.product.visible.port.dto.ProductDto;

public interface IProductReadInputPort {
  
  ProductDto findDetailsById(Integer id);

  Page<ProductDto> findByPage(int page, int size);

}
