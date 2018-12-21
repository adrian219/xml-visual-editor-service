package pl.adrianwieczorek.xmlvisualeditorservice.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.adrianwieczorek.xmlvisualeditorservice.util.XmlHelper;

public class ValidatorXml {

  @Autowired
  private XmlHelper xmlHelper;

  public Boolean check(String xml) {
    return xmlHelper.toDocument(xml) != null;
  }
}
