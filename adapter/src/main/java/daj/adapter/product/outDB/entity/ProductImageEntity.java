package daj.adapter.product.outDB.entity;

import daj.product.port.in.dto.RProductImage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image")
@NoArgsConstructor
public class ProductImageEntity extends RProductImage {

  @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
  public Integer getId() {
    return super.getId();
  }

	@Column(length = 64)
	@Override
  public String getName() {
    return super.getName();
  }

	@Column(length = 64)
	@Override
  public String getType() {
    return super.getType();
  }

	@Column(unique = false, nullable = false, length = 100000)
	@Override
  public byte[] getImage() {
    return super.getImage();
  }

  @Override
  @ManyToOne
  @JoinColumn(name = "id_product", nullable = false)
  public ProductEntity getProduct() {
    return (ProductEntity)super.getProduct();
  }
  
}
