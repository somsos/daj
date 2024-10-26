package daj.product.port.out;

import org.springframework.data.domain.Page;

import daj.product.port.in.dto.ProductModel;
import daj.product.port.in.dto.RProductImage;

public interface IProductReaderOutputPort {

  ProductModel findDetailsById(Integer input);

  Page<ProductModel> findByPage(int page, int size);

  RProductImage findImageByName(Integer id);

}
