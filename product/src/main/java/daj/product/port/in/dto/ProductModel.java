package daj.product.port.in.dto;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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

  @Range(min = 10, max = 100000)
  private Float price;

  @Range(min = 1, max = 100000)
  private Integer amount;

  @Length(min = 4, max = 64)
  private String description;

  private Date createdAt;

  private ProductModelSource source;
  
}
