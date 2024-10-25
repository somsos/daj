package daj.adapter.product.outDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import daj.adapter.product.outDB.entity.ProductEntity;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

  ProductEntity findByName(String name);
  
}
