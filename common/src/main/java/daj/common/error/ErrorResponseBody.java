package daj.common.error;

public class ErrorResponseBody {

  public final String message;

  public final String cause;

  public ErrorResponseBody(String msg, String causeArg) {
    this.message = msg;
    this.cause = causeArg;
  }

}