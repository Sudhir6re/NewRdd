package com.mahait.gov.in.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data 
public class MessageResponse  implements Serializable{
	
	private String response;
	private String style;
	private Integer statusCode;
	
}
