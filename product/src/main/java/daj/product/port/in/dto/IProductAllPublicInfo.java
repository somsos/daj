package daj.product.port.in.dto;

public interface IProductAllPublicInfo extends ProductSaveInfo, ProductSimpleInfo {

  IProductAllPublicInfo overwrite(ProductSaveInfo newInfo);
  
}
