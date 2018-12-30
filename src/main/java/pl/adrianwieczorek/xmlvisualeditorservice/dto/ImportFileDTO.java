package pl.adrianwieczorek.xmlvisualeditorservice.dto;

import lombok.Data;

@Data
public class ImportFileDTO {
  private XmlNodeDTO node;
  private String xml;
}
