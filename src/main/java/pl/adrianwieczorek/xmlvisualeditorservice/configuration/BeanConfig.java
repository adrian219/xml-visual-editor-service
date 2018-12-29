package pl.adrianwieczorek.xmlvisualeditorservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.adrianwieczorek.xmlvisualeditorservice.authentication.JwtAuthenticationFilter;
import pl.adrianwieczorek.xmlvisualeditorservice.mapper.OwnXmlDTOMapper;
import pl.adrianwieczorek.xmlvisualeditorservice.mapper.OwnXmlMapper;
import pl.adrianwieczorek.xmlvisualeditorservice.parser.InputXMLParser;
import pl.adrianwieczorek.xmlvisualeditorservice.parser.OutputXMLParser;
import pl.adrianwieczorek.xmlvisualeditorservice.util.XmlHelper;
import pl.adrianwieczorek.xmlvisualeditorservice.validation.ValidatorXml;

@Configuration
public class BeanConfig {

  @Bean
  public XmlHelper xmlHelper() {
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

  @Bean
  public ValidatorXml validatorXml() {
    return new ValidatorXml();
  }

  @Bean
  public JwtAuthenticationFilter authenticationTokenFilterBean() {
    return new JwtAuthenticationFilter();
  }

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    modelMapper.addMappings(new OwnXmlMapper());
    modelMapper.addMappings(new OwnXmlDTOMapper());

    return modelMapper;
  }
}
