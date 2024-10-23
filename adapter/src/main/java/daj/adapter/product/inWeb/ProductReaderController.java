package daj.adapter.product.inWeb;

import org.springframework.web.bind.annotation.RestController;

import daj.adapter.product.utils.ProductConstants;
import daj.common.error.ErrorResponse;
import daj.product.port.in.IProductReadInputPort;
import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductAllPublicInfo;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class ProductReaderController {

  private final IProductReadInputPort productReaderInputPort;

  
  @GetMapping(ProductWriterController.POINT_PRODUCTS_ID)
  public IProductAllPublicInfo findById(@PathVariable("id") Integer id) {
    final var found = productReaderInputPort.getById(id);
    if(found == null) {
      throw new ErrorResponse(ProductConstants.NOT_FOUND, 404, "not_found");
    }
    return found;
  }
  

  @GetMapping(ProductWriterController.POINT_PRODUCTS_BY_PAGE)
  public Page<ProductAllPublicInfo> findAll(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
        
    return productReaderInputPort.getProductsByPage(page, size);
  }

}
