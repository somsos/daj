package daj.product.port.in.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAllPublicInfo implements IProductAllPublicInfo {

  private Integer id;

  private String name;

  private Float price;

  private Integer amount;

  private String description;

  private Date createdAt;

  @Override
  public IProductAllPublicInfo overwrite(ProductSaveInfo newInfo) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'overwrite'");
  }
  
}
