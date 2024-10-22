package daj.adapter.product.inWeb;

import org.springframework.web.bind.annotation.RestController;

import daj.adapter.product.inWeb.reqAndRes.ProductsSaveRequest;
import daj.product.port.in.IProductInputPort;
import daj.product.port.in.dto.ProductResponseInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequiredArgsConstructor
public class ProductController {

  public static final String POINT_PRODUCTS = "/products";

  private final IProductInputPort inputPort;
  
  @PostMapping("/products")
  @ResponseStatus(HttpStatus.CREATED)
  public ProductResponseInfo save(@Valid @RequestBody ProductsSaveRequest input) {
    final var response = inputPort.save(input);
    return response;
  }

}
