package org.beckn.bap.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(pattern = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$")
public class Gps {
}
