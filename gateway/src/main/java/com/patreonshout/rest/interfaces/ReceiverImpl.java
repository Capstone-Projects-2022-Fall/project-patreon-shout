package com.patreonshout.rest.interfaces;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/receivers")
@Tag(name = "Receiver Service",
		description = "Handles all Webhook related functions.")
public interface ReceiverImpl {
}
