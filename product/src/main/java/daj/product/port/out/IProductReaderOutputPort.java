package daj.product.port.out;

import org.springframework.data.domain.Page;

import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductAllPublicInfo;

public interface IProductReaderOutputPort {

  IProductAllPublicInfo findById(Integer input);

  Page<ProductAllPublicInfo> findByPage(int page, int size);

}
