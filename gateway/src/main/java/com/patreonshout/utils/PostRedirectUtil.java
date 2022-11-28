package com.patreonshout.utils;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;

public class PostRedirectUtil {
	public static String convertHTMLPost(String postContent, String desiredPostFormat, Boolean realLineBreaks) {

		FlexmarkHtmlConverter converter = FlexmarkHtmlConverter.builder().build();

		String desiredLineBreak = realLineBreaks ? "\n" : "\\\n";

		// * Post content modifications
		String modifiedPostContent = converter.convert(postContent);
		modifiedPostContent = modifiedPostContent.replaceAll("\n\n", desiredLineBreak); // * <p></p> ends with double line breaks, replace that to one
		modifiedPostContent = modifiedPostContent.replaceAll("\n$", ""); // * If the post content ends in a line break, remove it

		String finalOutput = desiredPostFormat.replaceAll("\\{content}", modifiedPostContent); // * Replace {content} with the converted post contents
		// * Do NOT remove \\\\n, read https://stackoverflow.com/questions/1701839/string-replaceall-single-backslashes-with-double-backslashes
		finalOutput = finalOutput.replaceAll("(\\\\n|\\n|<br>|<br/>)", desiredLineBreak); // * Replace all text linebreaks with desiredLineBreak

		return finalOutput;
	}
}
