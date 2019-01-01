package pl.adrianwieczorek.xmlvisualeditorservice.parser;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.XmlNodeDTO;
import pl.adrianwieczorek.xmlvisualeditorservice.util.XmlHelper;

@Slf4j
public class OutputXMLParser {

  @Autowired
  private XmlHelper xmlHelper;

  public String toXML(XmlNodeDTO xmlNodeDTO) {
    StringBuilder sb = new StringBuilder();

    if(xmlNodeDTO == null) {
      return "";
    }

    return buildNode(sb, xmlNodeDTO);
  }

  public String format(String xml, Integer prettyPrintIndentFactor) {
    return xmlHelper.prettyFormat(xml, prettyPrintIndentFactor);
  }

  private String buildNode(StringBuilder sb, XmlNodeDTO xmlNodeDTO) {
    sb
            .append("<")
            .append(xmlNodeDTO.getName())
            .append(getStringParams(xmlNodeDTO));

    if ((xmlNodeDTO.getChildren() == null || xmlNodeDTO.getChildren().isEmpty()) && Strings.isNullOrEmpty(xmlNodeDTO.getContent())) {
      sb
              .append("/>");
    } else {
      sb
              .append(">");

      if (xmlNodeDTO.getChildren() != null && !xmlNodeDTO.getChildren().isEmpty()) {
        xmlNodeDTO.getChildren().forEach(node -> {
          StringBuilder childrenSb = new StringBuilder();
          sb.append(buildNode(childrenSb, node));
        });
      } else if (!Strings.isNullOrEmpty(xmlNodeDTO.getContent()) && !xmlNodeDTO.getContent().equals("null") && !xmlNodeDTO.getContent().equals("undefined")) { //todo ladniej napisac
        sb
                .append(xmlNodeDTO.getContent());
      }

      sb
              .append("</")
              .append(xmlNodeDTO.getName())
              .append(">");
    }

    return sb.toString();
  }

  private String getStringParams(XmlNodeDTO xmlNodeDTO) {
    StringBuilder sb = new StringBuilder();

    if (xmlNodeDTO.getParamKeys() != null && !xmlNodeDTO.getParamValues().isEmpty()) {
      sb
              .append(" ");

      for(int i = 0; i<xmlNodeDTO.getParamKeys().size(); i++){
        sb
                .append(xmlNodeDTO.getParamKeys().get(i))
                .append("=")
                .append("\"")
                .append(xmlNodeDTO.getParamValues().get(i))
                .append("\"")
                .append(" ");
      }
    }

    return sb.toString();
  }
}
