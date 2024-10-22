package daj.product.port.out;

import daj.product.port.in.dto.IProductAllPublicInfo;

public interface IProductReaderOutputPort {

  IProductAllPublicInfo findById(Integer input);

}
