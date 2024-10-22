package daj.user.service;

public interface IHasher {

  String encode(String rawPassword);

	boolean matches(String rawPassword, String encodedPassword);
  
}
