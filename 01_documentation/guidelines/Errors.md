# Errors

## Don't use Interfaces as dtos in Controllers or repositories

Spring use default classes (created with no-args-constructor) for its internals,
so if we use interfaces and can'r create the Request/Response/Entity will throw
this error.

```r
Could not resolve parameter [1] in public daj.product.port.in.dto.ProductModel daj.adapter.product.inWeb.ProductWriterController.update(java.lang.Integer,daj.product.port.in.dto.ProductModel): Type definition error: [simple type, class daj.product.port.in.dto.ProductModel]
```
