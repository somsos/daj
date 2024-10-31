package daj.user.port.in;

public interface IHasher {

  String encode(String rawPassword);

	boolean matches(String rawPassword, String encodedPassword);
  
}
