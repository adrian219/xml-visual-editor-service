package pl.adrianwieczorek.xmlvisualeditorservice.parser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.*;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.XmlNodeDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.util.XmlHelper;

import java.util.Random;

@Slf4j
public class InputXMLParser {

  @Autowired
  private XmlHelper xmlHelper;

  public XmlNodeDTO toNode(String xml) {
    Document doc = xmlHelper.toDocument(xml);

    return getNode(new XmlNodeDTO(), doc.getDocumentElement());
  }

  private XmlNodeDTO getNode(XmlNodeDTO xmlNodeDTO, Element e) {
    Random random = new Random();
    xmlNodeDTO.setId(random.nextLong());
    xmlNodeDTO.setName(e.getTagName());
    fillParameters(xmlNodeDTO, e);

    NodeList children = e.getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
      Node n = children.item(i);
      if (n.getNodeType() == Node.ELEMENT_NODE) {
        xmlNodeDTO.getChildren().add(getNode(new XmlNodeDTO(), (Element) n));
      } else {
        xmlNodeDTO.setContent(e.getTextContent().trim());
      }
    }

    return xmlNodeDTO;
  }

  private void fillParameters(XmlNodeDTO xmlNodeDTO, Element e) {
    NamedNodeMap attributes = e.getAttributes();
    for (Integer i = 0; i < attributes.getLength(); i++) {
      Node attr = attributes.item(i);
      xmlNodeDTO.getParamKeys().add(attr.getNodeName());
      xmlNodeDTO.getParamValues().add(attr.getNodeValue());
    }
  }
}
