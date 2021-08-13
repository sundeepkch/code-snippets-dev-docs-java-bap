package org.beckn.bap.dto;

import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(pattern = "[+-]?([0-9]*[.])?[0-9]+")
public class DecimalValue {
}
