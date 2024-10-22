package daj.product.port.in;

import daj.product.port.in.dto.IProductAllPublicInfo;

public interface IProductReadInputPort {
  
  IProductAllPublicInfo getById(Integer id);

}
