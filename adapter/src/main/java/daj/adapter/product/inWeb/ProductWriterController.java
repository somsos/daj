package daj.adapter.product.inWeb;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import daj.adapter.product.inWeb.reqAndRes.ProductSimpleResponse;
import daj.adapter.product.inWeb.reqAndRes.ProductsSaveRequest;
import daj.adapter.product.inWeb.reqAndRes.ProductsUpdateRequest;
import daj.adapter.product.outDB.entity.ProductEntity;
import daj.adapter.product.outDB.entity.ProductImageEntity;
import daj.common.utils.ImageUtility;
import daj.product.port.in.IProductWriteInputPort;
import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductSimpleInfo;
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
  
  @PostMapping("/products")
  @ResponseStatus(HttpStatus.CREATED)
  public ProductSimpleInfo save(@Valid @RequestBody ProductsSaveRequest input) {
    final var response = writerIP.save(input);
    return response;
  }

  @DeleteMapping(ProductWebConstants.POINT_PRODUCTS_ID)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ProductSimpleInfo delete(@PathVariable Integer id) {
    final ProductSimpleInfo deleted = writerIP.delete(id);
    return deleted;
  }

  @PutMapping(ProductWebConstants.POINT_PRODUCTS_ID)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public IProductAllPublicInfo update(@PathVariable("id") Integer id, @Valid @RequestBody ProductsUpdateRequest input) {
    final IProductAllPublicInfo newInfo = writerIP.update(id, input);
    return newInfo;
  }

  @PostMapping(ProductWebConstants.POINT_PRODUCTS_IMAGE)
  @ResponseStatus(HttpStatus.CREATED)
  public ProductSimpleResponse uploadImage(@PathVariable("id") Integer id, @RequestParam("image") MultipartFile file) throws IOException {
    final var imageFile = ImageUtility.compressImage(file.getBytes());
    
    var imageName = "defaultName";
    if(file.getOriginalFilename() != null) {
      imageName = file.getOriginalFilename();
    }
    
    final var imageEntity = new ProductImageEntity();
    imageEntity.setName(imageName);
    imageEntity.setType(file.getContentType());
    imageEntity.setImage(imageFile);
    
    ProductEntity product = new ProductEntity();
    product.setId(id);
    imageEntity.setProduct(product);

    final RProductImage imageSaved = writerIP.saveImage(imageEntity);

    final var response = new ProductSimpleResponse(imageSaved.getId(), "Image uploaded: " + imageName);
      
    return response;
  }

}
