package daj.adapter.product.utils;

import java.util.List;

import org.mapstruct.Mapper;

import daj.adapter.product.inWeb.reqAndRes.ProductSimpleResponse;
import daj.adapter.product.outDB.entity.ProductEntity;
import daj.adapter.product.outDB.entity.ProductImageEntity;
import daj.product.port.in.dto.ProductAllPublicInfo;
import daj.product.port.in.dto.ProductSaveInfo;
import daj.product.port.in.dto.RProductImage;

@Mapper
public interface ProductMapper {
  
  ProductEntity saveRequestToEntity(ProductSaveInfo source);

  ProductSimpleResponse entityToSimpleResponse(ProductEntity source);

  List<ProductAllPublicInfo> entitiesToAllPublicInfos(List<ProductEntity> source);

  ProductImageEntity RProductImageToEntity(RProductImage rProductImage);

  RProductImage entityToModel(ProductImageEntity saved);

}
