package com.patreonshout.rest;

import com.patreonshout.PSException;
import com.patreonshout.utils.ImageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageSvc extends BaseSvc {

	@GetMapping(path = "/images/blur", produces = "image/png")
	public ResponseEntity<?> blurImage(
			@RequestParam("image_url") String imageUrl,
			@RequestParam("radius") int radius,
			@RequestParam("text") String text,
			@RequestParam("text_color") String textColor
	) throws PSException {
		if (radius <= 0)
			throw new PSException(HttpStatus.BAD_REQUEST, "'radius' must be an integer greater than 0");

		return ResponseEntity
				.status(HttpStatus.OK)
				.contentType(MediaType.IMAGE_PNG)
				.body(ImageUtil.GenerateBlurredImage(imageUrl, radius, text, textColor));
	}
}
