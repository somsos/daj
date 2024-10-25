package daj.adapter.product.inWeb;

import org.springframework.web.bind.annotation.RestController;

import daj.adapter.product.utils.ProductConstants;
import daj.common.error.ErrorResponse;
import daj.common.utils.ImageUtility;
import daj.product.port.in.IProductReadInputPort;
import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductAllPublicInfo;
import daj.product.port.in.dto.RProductImage;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class ProductReaderController {

  private final IProductReadInputPort readerIP;

  
  @GetMapping(ProductWebConstants.POINT_PRODUCTS_ID)
  public IProductAllPublicInfo findById(@PathVariable("id") Integer id) {
    final var found = readerIP.getById(id);
    if(found == null) {
      throw new ErrorResponse(ProductConstants.NOT_FOUND, 404, "not_found");
    }
    return found;
  }
  

  @GetMapping(ProductWebConstants.POINT_PRODUCTS_BY_PAGE)
  public Page<ProductAllPublicInfo> findAll(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
        
    return readerIP.getProductsByPage(page, size);
  }

  @GetMapping(ProductWebConstants.POINT_PRODUCTS_IMAGE_ID)
  public ResponseEntity<byte[]> getImage(@PathVariable Integer id) throws IOException {

    final RProductImage image = readerIP.findImageByName(id);

    if(image == null) {
      throw new ErrorResponse(ProductWebConstants.ERROR_IMAGE_NOT_FOUND, 404, "not_found");
    }

    return ResponseEntity
      .ok()
      .contentType(MediaType.valueOf(image.getType()))
      .body(ImageUtility.decompressImage(image.getImage()));
  }

}
