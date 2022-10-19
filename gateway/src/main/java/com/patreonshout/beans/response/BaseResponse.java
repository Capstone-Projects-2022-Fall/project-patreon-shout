package com.patreonshout.beans.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class BaseResponse {

	private final int status;

	private final String message;

}
