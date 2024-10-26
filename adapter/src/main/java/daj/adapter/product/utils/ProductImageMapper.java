package daj.adapter.product.utils;

import org.mapstruct.Mapper;

import daj.adapter.product.outDB.entity.ProductImageEntity;
import daj.product.port.in.dto.ProductImageModel;


@Mapper
public interface ProductImageMapper {

  ProductImageEntity modelToEntity(ProductImageModel source);

  ProductImageModel entityToModel(ProductImageEntity source);
  
}
