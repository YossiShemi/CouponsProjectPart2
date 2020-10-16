package com.jb.CouponsProjectPart2.rest.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

	private long code;
    private String message;
    private String value;
    
}
