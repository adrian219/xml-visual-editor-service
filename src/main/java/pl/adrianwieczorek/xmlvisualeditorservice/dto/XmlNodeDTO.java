package pl.adrianwieczorek.xmlvisualeditorservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class XmlNodeDTO implements Serializable {
  private Long id;
  private String name;
  private List<String> paramKeys;
  private List<String> paramValues;
  private String content;
  private List<XmlNodeDTO> children;

  public XmlNodeDTO() {
    paramKeys = new ArrayList<>();
    paramValues = new ArrayList<>();
    children = new ArrayList<>();
  }
}