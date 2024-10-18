package daj.user.port.in.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class RegisterRDto {

  @NotBlank
  @Size(min = 3, max = 16)
  final private String username;

  @NotBlank
  @Size(min = 3, max = 16)
  final private String email;

  @NotBlank
  @Size(min = 3, max = 16)
  final private String password;
  
}
