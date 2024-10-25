package daj.adapter.product.inWeb;

public interface ProductWebConstants {
  
  String POINT_PRODUCTS = "/products";
  String POINT_PRODUCTS_ID = POINT_PRODUCTS + "/{id}";
  String POINT_PRODUCTS_BY_PAGE = POINT_PRODUCTS + "/page";
  String POINT_PRODUCTS_IMAGE = POINT_PRODUCTS_ID + "/image";
  String POINT_PRODUCTS_IMAGE_ID = POINT_PRODUCTS + "/image/{id}";

  String ERROR_IMAGE_NOT_FOUND = "Image not found";

}
