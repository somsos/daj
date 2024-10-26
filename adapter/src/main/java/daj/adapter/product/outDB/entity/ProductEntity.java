package daj.adapter.product.outDB.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import daj.product.port.in.dto.ProductModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class ProductEntity extends ProductModel {

  @Id()
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;


  @Column(unique=true, length=70, nullable=false)
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

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductImageEntity> images;

  public ProductEntity overwrite(ProductModel newInfo) {
    final var merged = new ProductEntity(this.id, this.name, this.price, this.amount, this.description, this.createdAt, this.images);

    if (newInfo.getAmount() != null) {
      merged.setAmount(newInfo.getAmount());
    }

    if (newInfo.getDescription() != null) {
      merged.setDescription(newInfo.getDescription());
    }

    if (newInfo.getName() != null) {
      merged.setName(newInfo.getName());
    }

    if (newInfo.getPrice() != null) {
      merged.setPrice(newInfo.getPrice());
    }

    return merged;
  }
  
}
