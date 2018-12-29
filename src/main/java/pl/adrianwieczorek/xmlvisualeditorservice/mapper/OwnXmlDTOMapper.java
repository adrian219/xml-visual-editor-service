package pl.adrianwieczorek.xmlvisualeditorservice.mapper;

import org.modelmapper.PropertyMap;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.OwnXml;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.OwnXmlDTO;

public class OwnXmlDTOMapper extends PropertyMap<OwnXmlDTO, OwnXml> {

  @Override
  protected void configure() {
    map().setId(source.getId());
    map().setName(source.getName());
    map().setXml(source.getXml());
  }
}