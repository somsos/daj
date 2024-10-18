package daj.adapter.user.outDB.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name = "users", uniqueConstraints = {
  @UniqueConstraint(columnNames = { "username" }, name = "username_constraint"),
  @UniqueConstraint(columnNames = { "email" }, name = "user_email_constraint"),
})
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class UserEntity {
  
  @Id()
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;


  @Column(length=16, unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(name="create_at")
  @Temporal(TemporalType.DATE)
  @CreationTimestamp
  private Date createdAt;

  @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
  @JoinTable(
    name="users_roles",
    joinColumns=@JoinColumn(name="user_id"),
    inverseJoinColumns=@JoinColumn(name="role_id"),
    uniqueConstraints= {@UniqueConstraint(columnNames={"user_id", "role_id"}) })
  private List<RoleEntity> roles;

}
