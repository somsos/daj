package daj.adapter.product.inWeb;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import daj.adapter.product.inWeb.reqAndResp.ProductActionResponse;
import daj.adapter.product.inWeb.reqAndResp.ProductSaveRequest;
import daj.adapter.product.inWeb.reqAndResp.ProductUpdateRequest;
import daj.adapter.product.outDB.entity.ProductImageEntity;
import daj.adapter.product.utils.ProductMapper;
import daj.common.utils.ImageUtility;
import daj.product.port.in.IProductWriteInputPort;
import daj.product.port.in.dto.ProductModel;
import daj.product.port.in.dto.RProductImage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequiredArgsConstructor
public class ProductWriterController {

  private final IProductWriteInputPort writerIP;

  private final ProductMapper mapper;
  
  @PostMapping("/products")
  @ResponseStatus(HttpStatus.CREATED)
  public ProductActionResponse save(@Valid @RequestBody ProductSaveRequest input) {
    final ProductModel mapped = mapper.saveRequestToEntity(input);
    final var saved = writerIP.save(mapped);
    final var response = new ProductActionResponse(saved.getId(), "product saved");
    return response;
  }

  @DeleteMapping(ProductWebConstants.POINT_PRODUCTS_ID)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ProductActionResponse delete(@PathVariable Integer id) {
    final ProductModel deleted = writerIP.delete(id);

    final var response = new ProductActionResponse(deleted.getId(), "product saved");
    return response;
  }

  @PutMapping(ProductWebConstants.POINT_PRODUCTS_ID)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ProductModel update(@PathVariable("id") Integer id, @Valid @RequestBody ProductUpdateRequest input) {
    final ProductModel mapped = mapper.updateRequestToEntity(input);
    final ProductModel newInfo = writerIP.update(id, mapped);
    return newInfo;
  }

  @PostMapping(ProductWebConstants.POINT_PRODUCTS_IMAGE)
  @ResponseStatus(HttpStatus.CREATED)
  public ProductActionResponse uploadImage(@PathVariable("id") Integer id, @RequestParam("image") MultipartFile file) throws IOException {
    final var imageFile = ImageUtility.compressImage(file.getBytes());
    
    var imageName = "defaultName";
    if(file.getOriginalFilename() != null) {
      imageName = file.getOriginalFilename();
    }
    
    final var imageEntity = new ProductImageEntity();
    imageEntity.setName(imageName);
    imageEntity.setType(file.getContentType());
    imageEntity.setImage(imageFile);
    
    ProductModel product = new ProductModel();
    product.setId(id);
    imageEntity.setProduct(product);

    final RProductImage imageSaved = writerIP.saveImage(imageEntity);

    final var response = new ProductActionResponse(imageSaved.getId(), "product image saved");

    return response;
  }

}
