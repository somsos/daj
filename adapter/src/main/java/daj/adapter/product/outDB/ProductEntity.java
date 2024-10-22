package daj.adapter.product.outDB;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import daj.product.port.in.dto.IProductAllPublicInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity implements IProductAllPublicInfo {

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

  @Column(name="created_at")
  @Temporal(TemporalType.DATE)
  @CreationTimestamp
  private Date createdAt;
  
}
