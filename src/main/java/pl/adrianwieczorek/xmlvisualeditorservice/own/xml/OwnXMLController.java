package pl.adrianwieczorek.xmlvisualeditorservice.own.xml;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.RestAPIConstants;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.OwnXmlDTO;

import java.util.List;

@RestController
@RequestMapping(RestAPIConstants.OWN_XMLS)
@Slf4j
public class OwnXMLController {

  @Autowired
  private OwnXMLService ownXMLService;

  @GetMapping
  public List<OwnXmlDTO> getXmls(@AuthenticationPrincipal UserDetails userDetails) {
    return ownXMLService.findAllByUsername(userDetails.getUsername());
  }

  @PostMapping
  public OwnXmlDTO addXml(@RequestBody OwnXmlDTO ownXML, @AuthenticationPrincipal UserDetails userDetails) {
    return ownXMLService.create(ownXML, userDetails.getUsername());
  }

  @GetMapping("/{id}")
  public OwnXmlDTO getXml(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
    //checking forbidding
    return ownXMLService.get(id);
  }

  @PostMapping("/{id}")
  public OwnXmlDTO saveXml(@PathVariable Long id, @RequestBody OwnXmlDTO ownXML, @AuthenticationPrincipal UserDetails userDetails) {
    //checking forbidding
    return ownXMLService.save(ownXML);
  }

  @DeleteMapping("/{id}")
  public void deleteXml(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
    //checking forbidding
    ownXMLService.delete(id);
  }
}
