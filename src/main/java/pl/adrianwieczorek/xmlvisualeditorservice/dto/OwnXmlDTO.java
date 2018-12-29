package pl.adrianwieczorek.xmlvisualeditorservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.RestDateConstants;

import java.time.LocalDateTime;

@Data
public class OwnXmlDTO {
  private Long id;
  private String xml;
  private String name;
  private Long userId;
  @JsonFormat(pattern = RestDateConstants.ISO_DATE_TIME_WITHOUT_TIMEZONE_FORMAT)
  private LocalDateTime lastUpdated;
}
