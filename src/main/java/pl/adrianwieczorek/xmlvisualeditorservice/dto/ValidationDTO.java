package pl.adrianwieczorek.xmlvisualeditorservice.dto;

import lombok.Data;

@Data
public class ValidationDTO {
  private String xml;
  private Boolean validate;
  private String errorMessage;
}
