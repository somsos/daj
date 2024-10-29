package daj.adapter.product.outDB.entity;

import java.util.Date;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_images")
@SQLDelete(sql = "UPDATE products SET deleted_at = now() WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
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

  @Column(name="deleted_at", nullable = true)
  @Temporal(TemporalType.DATE)
  private Date deletedAt;
  
}
