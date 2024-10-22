package daj.adapter.product.inWeb.reqAndRes;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import daj.product.port.in.dto.IProductAllPublicInfo;

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
  
}
