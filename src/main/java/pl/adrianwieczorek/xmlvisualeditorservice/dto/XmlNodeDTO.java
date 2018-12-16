package pl.adrianwieczorek.xmlvisualeditorservice.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlNodeDTO implements Serializable {
  private Long id;
  private String name;
  private Map<String, String> params;
  private String content;
  private List<XmlNodeDTO> children;

  public XmlNodeDTO() {
    params = new HashMap<>();
    children = new ArrayList<>();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public void setParams(Map<String, String> params) {
    this.params = params;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<XmlNodeDTO> getChildren() {
    return children;
  }

  public void setChildren(List<XmlNodeDTO> children) {
    this.children = children;
  }
}
