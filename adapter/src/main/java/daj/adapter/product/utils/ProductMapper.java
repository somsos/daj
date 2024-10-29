package daj.adapter.product.utils;

import java.util.List;
import java.util.ArrayList;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import daj.adapter.product.inWeb.reqAndResp.ProductDetailsResponse;
import daj.adapter.product.inWeb.reqAndResp.ProductSaveRequest;
import daj.adapter.product.inWeb.reqAndResp.ProductUpdateRequest;
import daj.adapter.product.outDB.entity.ProductEntity;
import daj.product.port.in.dto.ProductImageModel;
import daj.product.port.in.dto.ProductModel;


@Mapper
public interface ProductMapper {

  @Mapping(ignore = true, target = "images")
  ProductModel entityToModel(ProductEntity source);

  @Mapping(ignore = true, target = "images")
  ProductEntity modelToEntity(ProductModel entitySource);

  //user input
  ProductModel saveRequestToEntity(ProductSaveRequest source);

  ProductModel updateRequestToEntity(ProductUpdateRequest source);

  //user output
  ProductDetailsResponse modelToDetails(ProductModel m);

  List<ProductDetailsResponse> listModelsToDetails(List<ProductModel> source);

  default List<Integer> mapImages(List<ProductImageModel> images) {
    if(images == null) {
      return new ArrayList<Integer>();
    }
    List<Integer> listIdImages = images.stream()
      .map(m -> m.getId()).toList();
    return listIdImages;
  }  

  List<ProductModel> listEntitiesToModels(List<ProductEntity> content);
  

}