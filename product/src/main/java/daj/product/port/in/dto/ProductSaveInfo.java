package daj.product.port.in.dto;



public interface ProductSaveInfo {

  String getName();
  
  Float getPrice();
  
  Integer getAmount();

  String getDescription();

  //

  void setName(String n);
  
  void setPrice(Float p);
  
  void setAmount(Integer a);

  void setDescription(String d);

}

/*
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
public class ProductSaveRDto {

  @NotBlank
  @Size(min = 4, max = 70)
  final private String name;

  @NotNull
  @Range(min = 10, max = 100000)
  final private Float price;

  @NotNull
  @Range(min = 1, max = 100000)
  final private Integer amount;

  @NotBlank
  @Size(min = 4, max = 255)
  final private String description;

}
 */