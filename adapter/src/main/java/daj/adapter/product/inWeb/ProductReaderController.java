package daj.adapter.product.inWeb;

import org.springframework.web.bind.annotation.RestController;

import daj.adapter.product.inWeb.reqAndResp.ProductDetailsResponse;
import daj.adapter.product.utils.ProductConstants;
import daj.adapter.product.utils.ProductMapper;
import daj.common.error.ErrorResponse;
import daj.common.utils.ImageUtility;
import daj.product.port.in.IProductReadInputPort;
import daj.product.port.in.dto.ProductImageModel;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class ProductReaderController {

  private final IProductReadInputPort readerIP;

  private final ProductMapper mapper;
  
  @GetMapping(ProductWebConstants.POINT_PRODUCTS_ID)
  public ProductDetailsResponse findDetailsById(@PathVariable("id") Integer id) {
    final var found = readerIP.findDetailsById(id);
    if(found == null) {
      throw new ErrorResponse(ProductConstants.NOT_FOUND, 404, "not_found");
    }

    final var details = mapper.modelToDetails(found);
    return details;
  }
  

  @GetMapping(ProductWebConstants.POINT_PRODUCTS_BY_PAGE)
  public Page<ProductDetailsResponse> findByPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    final var pageFound = readerIP.findByPage(page, size);

    //create response
    final var contentMapped = mapper.listModelsToDetails(pageFound.getContent());
    final var pageResponse = new PageImpl<ProductDetailsResponse>(contentMapped, pageFound.getPageable(), pageFound.getSize());
    return pageResponse;
  }

  @GetMapping(ProductWebConstants.POINT_PRODUCTS_IMAGE_ID)
  public ResponseEntity<byte[]> getImage(@PathVariable Integer id) throws IOException {

    final ProductImageModel image = readerIP.findImageById(id);

    if(image == null) {
      throw new ErrorResponse(ProductWebConstants.ERROR_IMAGE_NOT_FOUND, 404, "not_found");
    }

    return ResponseEntity
      .ok()
      .contentType(MediaType.valueOf(image.getType()))
      .body(ImageUtility.decompressImage(image.getImage()));
  }

}
