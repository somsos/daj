package daj.adapter.product.utils;

import org.mapstruct.Mapper;

import daj.adapter.product.inWeb.reqAndResp.ProductSaveRequest;
import daj.adapter.product.inWeb.reqAndResp.ProductUpdateRequest;
import daj.adapter.product.outDB.entity.ProductEntity;
import daj.product.port.in.dto.ProductModel;


@Mapper
public interface ProductMapper {

  ProductModel entityToModel(ProductEntity source);

  ProductEntity modelToEntity(ProductModel source);

  //user input
  ProductEntity saveRequestToEntity(ProductSaveRequest source);

  ProductEntity updateRequestToEntity(ProductUpdateRequest source);

}