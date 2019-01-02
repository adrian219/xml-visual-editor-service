package pl.adrianwieczorek.xmlvisualeditorservice.authorization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.RestAPIConstants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class OwnerXmlAuthorizationFilter extends OncePerRequestFilter {

  @Autowired
  private SecurityOwnerXmlService securityOwnerXmlService;

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
    Long ownXmlId = getOwnXmlId(httpServletRequest.getRequestURI());

    Boolean ifOwner = securityOwnerXmlService.checkIfOwnerXml(ownXmlId, httpServletRequest.getUserPrincipal().getName());

    if(!ifOwner) {
      httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();
    return !path.contains(RestAPIConstants.OWN_XMLS + "/");
  }

  private Long getOwnXmlId(String path) {
    Pattern pattern = Pattern.compile("(" + RestAPIConstants.OWN_XMLS + "/)([0-9]+)");
    Matcher matcher = pattern.matcher(path);
    if (matcher.find()) {
      return Long.parseLong(matcher.group(2));
    } else {
      throw new IllegalArgumentException("Cannot parse http request");
    }
  }
}
