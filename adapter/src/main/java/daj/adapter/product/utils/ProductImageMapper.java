package daj.adapter.product.utils;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;

import daj.adapter.product.outDB.entity.ProductImageEntity;
import daj.product.port.in.dto.ProductImageModel;


@Mapper
public interface ProductImageMapper {

  ProductImageEntity modelToEntity(ProductImageModel source);

  //@Mapping(ignore = true, target = "image")
  ProductImageModel entityToModel(ProductImageEntity source);
  
  //make a recoursion error
  //List<ProductImageModel> listEntitiesToModels(List<ProductImageEntity> images);

  default List<ProductImageModel> mapImages(List<ProductImageEntity> images) {
    if(images == null) {
      return new ArrayList<ProductImageModel>();
    }
    List<ProductImageModel> listIdImages = images.stream()
      .map(m -> {
        final var c = new ProductImageModel();
        c.setId(m.getId());
        return c;
      }).toList();
    return listIdImages;
  }  
  
}
