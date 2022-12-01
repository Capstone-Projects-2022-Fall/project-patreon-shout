package com.patreonshout.utils;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;

/**
 * Contains Post Redirect related functions
 */
public class PostRedirectUtil {

	/**
	 * Creates a {@link String} containing text that is formulated into social integration redirect-able text
	 *
	 * @param postContent {@link com.patreonshout.beans.patreon_api.PatreonPostV2} post content
	 * @param desiredPostFormat {@link String} public or private message belonging to a {@link com.patreonshout.beans.SocialIntegrationMessages}
	 * @param realLineBreaks {@link Boolean} if real line breaks are desired
	 * @return {@link String} social integration formatted text
	 */
	public static String convertHTMLPost(String postContent, String postUrl, String desiredPostFormat, Boolean realLineBreaks, Boolean reddit) {

		FlexmarkHtmlConverter converter = FlexmarkHtmlConverter.builder().build();

		String desiredLineBreak = realLineBreaks ? "\n" : "\\\n";

		// * Post content modifications
		String modifiedPostContent = converter.convert(postContent);
		modifiedPostContent = modifiedPostContent.replaceAll("\n\n", desiredLineBreak); // * <p></p> ends with double line breaks, replace that to one
		modifiedPostContent = modifiedPostContent.replaceAll("\n$", ""); // * If the post content ends in a line break, remove it

		String finalOutput = desiredPostFormat.replaceAll("\\{link}", "https://www.patreon.com" + postUrl); // * Replace {link} with the post's url
		finalOutput = finalOutput.replaceAll("\\{content}", modifiedPostContent); // * Replace {content} with the converted post contents
		// * Do NOT remove \\\\n, read https://stackoverflow.com/questions/1701839/string-replaceall-single-backslashes-with-double-backslashes
		finalOutput = finalOutput.replaceAll("(\\\\n|\\n|<br>|<br/>)", reddit ? "  \n" : desiredLineBreak); // * Replace all text linebreaks with desiredLineBreak

		return finalOutput;
	}
}
