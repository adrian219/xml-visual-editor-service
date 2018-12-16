package pl.adrianwieczorek.xmlvisualeditorservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.adrianwieczorek.xmlvisualeditorservice.parser.InputXMLParser;
import pl.adrianwieczorek.xmlvisualeditorservice.parser.OutputXMLParser;
import pl.adrianwieczorek.xmlvisualeditorservice.util.XmlHelper;

@Configuration
public class BeanConfig {

  @Bean
  public XmlHelper xmlHelper(){
    return new XmlHelper();
  }

  @Bean
  public InputXMLParser inputXMLParser() {
    return new InputXMLParser();
  }

  @Bean
  public OutputXMLParser outputXMLParser() {
    return new OutputXMLParser();
  }
}
