package pl.adrianwieczorek.xmlvisualeditorservice.util;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import pl.adrianwieczorek.xmlvisualeditorservice.validation.ValidationException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Slf4j
public class XmlHelper {
  private static final Integer DEFAULT_PRETTY_STRING_FACTOR = 4;

  public Document toDocument(String xml) {
    if (Strings.isNullOrEmpty(xml)) {
      log.warn("Xml is empty!");
      return null;
    } else {
      try {
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return docBuilder.parse(is);
      } catch (SAXParseException e) {
        throw new ValidationException(String.format("Line number: %s, Column number: %s, Message from server: %s", e.getColumnNumber(), e.getLineNumber(), e.getLocalizedMessage()));
      } catch (ParserConfigurationException | IOException | SAXException e) {
        log.error("Cannot parse XML from string [xml={}]", xml);
        log.error(e.getMessage());
        throw new ValidationException(e.toString());
      }
    }
  }

  public String prettyFormat(String xml, Integer prettyPrintIndentFactor) {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty(
              "{http://xml.apache.org/xslt}indent-amount", prettyPrintIndentFactor.toString());
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      DOMSource source = new DOMSource(toDocument(xml));
      StringWriter strWriter = new StringWriter();
      StreamResult result = new StreamResult(strWriter);

      transformer.transform(source, result);

      return strWriter.getBuffer().toString();
    } catch (TransformerException e) {
      log.error("Cannot parse xml to pretty format [xml={}, prettyPrintIndentFactor={}]", xml, prettyPrintIndentFactor);
      log.error(e.getMessage());
      return null;
    }
  }

  public String prettyFormat(String xml) {
    return prettyFormat(xml, DEFAULT_PRETTY_STRING_FACTOR);
  }
}
