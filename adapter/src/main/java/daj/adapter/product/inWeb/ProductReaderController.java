package daj.adapter.product.inWeb;

import org.springframework.web.bind.annotation.RestController;

import daj.product.port.in.IProductReadInputPort;
import daj.product.port.in.dto.IProductAllPublicInfo;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequiredArgsConstructor
public class ProductReaderController {

  private final IProductReadInputPort productReaderInputPort;

  
  @GetMapping(ProductWriterController.POINT_PRODUCTS_ID)
  public IProductAllPublicInfo findById(@PathVariable("id") Integer id) {
    final var found = productReaderInputPort.getById(id);
    return found;
  }
  


}
