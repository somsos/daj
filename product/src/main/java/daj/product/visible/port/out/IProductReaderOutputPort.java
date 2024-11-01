package daj.product.visible.port.out;

import org.springframework.data.domain.Page;

import daj.product.visible.port.dto.ProductDto;

public interface IProductReaderOutputPort {

  ProductDto findDetailsById(Integer input);

  Page<ProductDto> findByPage(int page, int size);

  

}
