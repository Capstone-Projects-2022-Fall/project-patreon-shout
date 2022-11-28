package com.patreonshout.rest.interfaces;

import com.patreonshout.PSException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/images")
@Tag(name = "Image Service",
		description = "Handles all image related functions.")
public interface ImageImpl {

	/**
	 * Generates an image with the Gaussian Blur filter
	 *
	 * @param imageUrl {@link String} image URL of the desired image
	 * @param radius {@link Integer} blur amount
	 * @param text {@link String} text to overlay on top of blurred image
	 * @param textColor {@link String} HEX color value (including # symbol)
	 * @return Array of bytes containing the raw Image data
	 */
	@GetMapping(path = "/images/blur", produces = "image/png")
	@Operation(summary = "Generates an image with the Gaussian Blur filter")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Image generated and returned successfully."),
			@ApiResponse(responseCode = "400",
					description = "Blur amount/radius was an invalid amount.")
	})
	ResponseEntity<?> blurImage(
			@RequestParam("image_url") String imageUrl,
			@RequestParam("radius") int radius,
			@RequestParam("text") String text,
			@RequestParam("text_color") String textColor
	) throws PSException;
}
