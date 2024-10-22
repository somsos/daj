package daj.adapter.product.outDB;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import daj.product.port.in.dto.ProductResponseInfo;
import daj.product.port.in.dto.ProductSaveInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "products")
@Getter
@Setter
@AllArgsConstructor
public class ProductEntity implements ProductSaveInfo, ProductResponseInfo {

  @Id()
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;


  @Column(unique=true, length=20, nullable=false)
  private String name;

  @Column(nullable=false)
  @Range(min = 10, max = 100000)
  private Float price;

  @Column(nullable=false)
  @Range(min = 10, max = 100000)
  private Integer amount;

  @Column(length=256)
  private String description;

  @Column(name="create_at")
  @Temporal(TemporalType.DATE)
  @CreationTimestamp
  private Date createdAt;
  
}
