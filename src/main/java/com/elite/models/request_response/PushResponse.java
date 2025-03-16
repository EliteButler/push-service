package com.elite.models.request_response;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class PushResponse {
	@JsonProperty("msg")
	public String message;
}
