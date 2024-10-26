package daj.product.port.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageModel {

  private Integer id;

  private String name;

  private String type;

  private byte[] image;

  private ProductModel product;
  
}