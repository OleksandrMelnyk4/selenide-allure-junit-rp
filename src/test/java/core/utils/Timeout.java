package core.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Timeout {
  SEC_5(5),
  MILISEC_100(100),
  MILISEC_200(200),
  MILISEC_400(400),
  HALF_SEC(500),
  TWO_SEC(2000),
  FIVE_SEC(5000),
  EIGHT_SEC(8000),
  TEN_SEC(10000),
  TWENTY_SEC(20000),
  HALF_MINUTE(30000),
  ONE_MINUTE(60000),
  TWO_MINUTES(120000),
  THREE_MINUTES(180000),
  THIRTY_MINUTES(1800000);

  private final long value;
}
