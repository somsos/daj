package daj.product.port.out;

import org.springframework.data.domain.Page;

import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductAllPublicInfo;
import daj.product.port.in.dto.RProductImage;

public interface IProductReaderOutputPort {

  IProductAllPublicInfo findByIdOrThrow(Integer input);

  Page<ProductAllPublicInfo> findByPage(int page, int size);

  RProductImage findImageByName(Integer id);

}
