package daj.product.port.in;

import org.springframework.data.domain.Page;

import daj.product.port.in.dto.ProductModel;
import daj.product.port.in.dto.ProductImageModel;
import jakarta.validation.constraints.NotNull;

public interface IProductReadInputPort {
  
  ProductModel findDetailsById(Integer id);

  Page<ProductModel> getProductsByPage(int page, int size);

  ProductImageModel findImageByName(@NotNull Integer id);

}
