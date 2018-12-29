package pl.adrianwieczorek.xmlvisualeditorservice.mapper;

import org.modelmapper.PropertyMap;
import pl.adrianwieczorek.xmlvisualeditorservice.domain.OwnXml;
import pl.adrianwieczorek.xmlvisualeditorservice.dto.OwnXmlDTO;

public class OwnXmlMapper extends PropertyMap<OwnXml, OwnXmlDTO> {

  @Override
  protected void configure() {
    map().setId(source.getId());
    map().setName(source.getName());
    map().setXml(source.getXml());
    map().setUserId(source.getUser().getId());
    map().setLastUpdated(source.getLastModifiedDate());
  }
}