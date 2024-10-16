package daj.adapter.user.config;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import daj.common.error.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FilterErrorHandler extends OncePerRequestFilter {

  @SuppressWarnings("null")
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    try {
      filterChain.doFilter(request, response);
    } catch (ErrorResponse ex) {
      this.respondUserError(response, ex);
    } catch (Exception e) {
      this.respondeUnknownError(response, e);
    }
  }

  private void respondUserError(HttpServletResponse response, ErrorResponse ex) {
    Map<String, String> errorDetails = new HashMap<>();
    errorDetails.put("message", ex.getMessage());
    errorDetails.put("cause", ex.getCause().getLocalizedMessage());
    log.warn("handled in FilterErrorHandler.respondUserError: " + ex.getMessage());
    this.sendResponse(response, errorDetails, ex.getHttpStatus());
  }

  private void respondeUnknownError(HttpServletResponse response, Exception e) {
    Map<String, String> errorDetails = new HashMap<>();
    errorDetails.put("message", "Internal error, try later or contact admins");
    errorDetails.put("cause", e.getLocalizedMessage().replace("\"", ""));
    errorDetails.put("exType", e.getClass().getName());
    log.warn("handled in FilterErrorHandler.respondeUnknownError: " + e.getMessage());
    this.sendResponse(response, errorDetails, 500);
  }

  private void sendResponse(HttpServletResponse response, Map<String, String> body, int status) {

    response.setStatus(status);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    try {
      final var mapper = new ObjectMapper();
      response.getWriter().write(mapper.writeValueAsString(body));
    } catch (Exception e) {
      final String message = "Internal error, try later or contact admins";
      final String cause = "Error converting filter error to json";
      final String json = String.format("{\"cause\":\"%s\",\"message\":\"%s\"}", cause, message);
      try {
        response.getWriter().write(json);
      } catch (IOException e1) {
        throw new RuntimeException("Fatal as-cd-vf-5t-kj");
      }
    }

  }

}