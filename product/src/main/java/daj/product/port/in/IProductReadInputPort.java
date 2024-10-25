package daj.product.port.in;

import org.springframework.data.domain.Page;

import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductAllPublicInfo;
import daj.product.port.in.dto.RProductImage;
import jakarta.validation.constraints.NotNull;

public interface IProductReadInputPort {
  
  IProductAllPublicInfo getById(Integer id);

  Page<ProductAllPublicInfo> getProductsByPage(int page, int size);

  RProductImage findImageByName(@NotNull Integer id);

}
