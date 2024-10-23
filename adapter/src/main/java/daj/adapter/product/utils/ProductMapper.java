package daj.adapter.product.utils;

import java.util.List;

import org.mapstruct.Mapper;

import daj.adapter.product.inWeb.reqAndRes.ProductSimpleResponse;
import daj.adapter.product.outDB.ProductEntity;
import daj.product.port.in.dto.ProductAllPublicInfo;
import daj.product.port.in.dto.ProductSaveInfo;

@Mapper
public interface ProductMapper {
  
  ProductEntity saveRequestToEntity(ProductSaveInfo source);

  ProductSimpleResponse entityToSimpleResponse(ProductEntity source);

  List<ProductAllPublicInfo> entitiesToAllPublicInfos(List<ProductEntity> source);

}
