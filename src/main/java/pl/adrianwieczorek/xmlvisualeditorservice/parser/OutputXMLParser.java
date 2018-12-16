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
    }

    if (xmlNodeDTO.getChildren() != null && !xmlNodeDTO.getChildren().isEmpty()) {
      xmlNodeDTO.getChildren().forEach(node -> buildNode(sb, node));
      sb
              .append("</")
              .append(xmlNodeDTO.getName())
              .append(">");
    } else if (!Strings.isNullOrEmpty(xmlNodeDTO.getContent())) {
      sb
              .append(xmlNodeDTO.getContent());
    }

    return sb.toString();
  }

  private String getStringParams(XmlNodeDTO xmlNodeDTO) {
    StringBuilder sb = new StringBuilder();

    if (xmlNodeDTO.getParams() != null && !xmlNodeDTO.getParams().isEmpty()) {
      sb
              .append(" ");

      xmlNodeDTO.getParams().forEach((k, v) -> {
        sb
                .append(k)
                .append("=")
                .append("\"")
                .append(v)
                .append("\"")
                .append(" ");
      });
    }

    return sb.toString();
  }
}
