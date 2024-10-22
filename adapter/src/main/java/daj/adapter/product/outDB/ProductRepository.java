package daj.adapter.product.outDB;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

  ProductEntity findByName(String name);
  
}
