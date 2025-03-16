package com.elite.models.request_response;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class PushRequest {
	public String payload;
	public int priority;
	public String medium;
}

