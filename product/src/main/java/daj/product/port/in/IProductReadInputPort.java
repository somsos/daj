package daj.product.port.in;

import org.springframework.data.domain.Page;

import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductAllPublicInfo;

public interface IProductReadInputPort {
  
  IProductAllPublicInfo getById(Integer id);

  Page<ProductAllPublicInfo> getProductsByPage(int page, int size);

}
