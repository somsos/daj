package daj.adapter.product.utils;

import org.mapstruct.Mapper;

import daj.adapter.product.outDB.entity.ProductImageEntity;
import daj.product.port.in.dto.RProductImage;


@Mapper
public interface ProductImageMapper {

  ProductImageEntity modelToEntity(RProductImage source);

  RProductImage entityToModel(ProductImageEntity source);
  
}
