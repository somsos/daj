package daj.adapter.product.inWeb;

import org.springframework.web.bind.annotation.RestController;

import daj.adapter.product.inWeb.reqAndResp.ProductDetailsResponse;
import daj.adapter.product.utils.ProductMapper;
import daj.common.error.ErrorResponse;
import daj.product.visible.config.IProductConstants;
import daj.product.visible.port.in.IProductReadInputPort;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class ProductReaderController {

  private final IProductReadInputPort readerIP;

  private final ProductMapper mapper;
  
  @GetMapping(IProductConstants.POINT_PRODUCTS_ID)
  public ProductDetailsResponse findDetailsById(@PathVariable("id") Integer id) {
    final var found = readerIP.findDetailsById(id);
    if(found == null) {
      throw new ErrorResponse(IProductConstants.NOT_FOUND, 404, "not_found");
    }

    final var details = mapper.modelToDetails(found);
    return details;
  }
  

  @GetMapping(IProductConstants.POINT_PRODUCTS_BY_PAGE)
  public Page<ProductDetailsResponse> findByPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    final var pageFound = readerIP.findByPage(page, size);

    //create response
    final var contentMapped = mapper.listModelsToDetails(pageFound.getContent());
    final var pageResponse = new PageImpl<ProductDetailsResponse>(contentMapped, pageFound.getPageable(), pageFound.getSize());
    return pageResponse;
  }

}
