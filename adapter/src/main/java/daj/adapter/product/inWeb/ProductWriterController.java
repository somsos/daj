package daj.adapter.product.inWeb;

import org.springframework.web.bind.annotation.RestController;

import daj.adapter.product.inWeb.reqAndRes.ProductsSaveRequest;
import daj.adapter.product.inWeb.reqAndRes.ProductsUpdateRequest;
import daj.product.port.in.IProductWriteInputPort;
import daj.product.port.in.dto.IProductAllPublicInfo;
import daj.product.port.in.dto.ProductSimpleInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequiredArgsConstructor
public class ProductWriterController {

  public static final String POINT_PRODUCTS = "/products";
  public static final String POINT_PRODUCTS_ID = POINT_PRODUCTS + "/{id}";

  private final IProductWriteInputPort writerIP;
  
  @PostMapping("/products")
  @ResponseStatus(HttpStatus.CREATED)
  public ProductSimpleInfo save(@Valid @RequestBody ProductsSaveRequest input) {
    final var response = writerIP.save(input);
    return response;
  }

  @DeleteMapping(POINT_PRODUCTS_ID)
  public ProductSimpleInfo delete(@PathVariable Integer id) {
    final ProductSimpleInfo deleted = writerIP.delete(id);
    return deleted;
  }

  @PutMapping(POINT_PRODUCTS_ID)
  public IProductAllPublicInfo update(@PathVariable("id") Integer id, @Valid @RequestBody ProductsUpdateRequest input) {
    final IProductAllPublicInfo newInfo = writerIP.update(id, input);
    return newInfo;
  }

}
