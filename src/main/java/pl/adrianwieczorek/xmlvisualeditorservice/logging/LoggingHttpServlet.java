package pl.adrianwieczorek.xmlvisualeditorservice.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
public class LoggingHttpServlet extends DispatcherServlet {

  @Autowired
  private LogRepository logRepository;

  @Override
  protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String user = request.getRemoteUser();

    if (!(request instanceof ContentCachingRequestWrapper)) {
      request = new ContentCachingRequestWrapper(request);
    }
    if (!(response instanceof ContentCachingResponseWrapper)) {
      response = new ContentCachingResponseWrapper(response);
    }

    try {
      super.doDispatch(request, response);
    } finally {
      log(request, response, user);
      updateResponse(response);
    }
  }

  private void log(HttpServletRequest requestToCache, HttpServletResponse responseToCache, String user) {
    Log log = new Log();
    log.setCreatedBy(user);
    log.setLastModifiedBy(user);
    log.setHttpStatus(responseToCache.getStatus());
    log.setHttpMethod(requestToCache.getMethod());
    log.setPath(requestToCache.getRequestURI());
    log.setClientIp(requestToCache.getRemoteAddr());
    log.setRequest(getRequestPayload(requestToCache));
    log.setResponse(getResponsePayload(responseToCache));
//    logger.info(log); //todo do usuniecia
    logRepository.save(log);
  }

  private String getRequestPayload(HttpServletRequest request) {
    ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
    if (wrapper != null) {

      byte[] buf = wrapper.getContentAsByteArray();
      if (buf.length > 0) {
        int length = Math.min(buf.length, 5120);
        try {
          return new String(buf, 0, length, wrapper.getCharacterEncoding());
        }
        catch (UnsupportedEncodingException ex) {
          // NOOP
        }
      }
    }
    return "[unknown]";
  }

  private String getResponsePayload(HttpServletResponse response) {
    ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
    if (wrapper != null) {

      byte[] buf = wrapper.getContentAsByteArray();
      if (buf.length > 0) {
        int length = Math.min(buf.length, 5120);
        try {
          return new String(buf, 0, length, wrapper.getCharacterEncoding());
        }
        catch (UnsupportedEncodingException ex) {
          // NOOP
        }
      }
    }
    return "[unknown]";
  }

  private void updateResponse(HttpServletResponse response) throws IOException {
    ContentCachingResponseWrapper responseWrapper =
            WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
    responseWrapper.copyBodyToResponse();
  }
}
