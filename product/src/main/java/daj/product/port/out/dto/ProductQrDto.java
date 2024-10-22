package daj.product.port.out.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductQrDto {
  
  final private Integer id;

  final private String name;

  final private Float price;

  final private Integer amount;

  final private String description;

}
