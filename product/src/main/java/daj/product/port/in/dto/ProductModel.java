package daj.product.port.in.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

  private Integer id;

  private String name;

  private Float price;

  private Integer amount;

  private String description;

  private Date createdAt;

  private List<ProductImageModel> images;

  private ProductModelSource source;
  
}
