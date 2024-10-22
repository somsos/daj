package daj.product.port.in;

import daj.product.port.in.dto.ProductResponseInfo;
import daj.product.port.in.dto.ProductSaveInfo;

public interface IProductInputPort {

  ProductResponseInfo save(ProductSaveInfo input);

}
