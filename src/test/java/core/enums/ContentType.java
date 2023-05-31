package core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContentType {

  APPLICATION_XML("application/xml"),

  APPLICATION_JSON("application/json;charset=utf-8"),

  APPLICATION_PDF("application/pdf;charset=UTF-8"),

  APPLICATION_OCTET_STREAM("application/octet-stream"),

  FORM_DATA_WITH_CHARSET("application/x-www-form-urlencoded; charset=UTF-8"),

  ANY("*/*");

  private final String value;
}
