package daj.adapter.product.outDB.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductImageEntity {

  @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 64)
  private String name;

  @Column(length = 32)
  private String type;

  @Column(unique = false, nullable = false, length = 100000)
  private byte[] image;

  @ManyToOne
  @JoinColumn(name = "id_product", nullable = false)
  private ProductEntity product;
  
}
