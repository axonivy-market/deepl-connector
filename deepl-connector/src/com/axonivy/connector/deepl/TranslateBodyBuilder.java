package com.axonivy.connector.deepl;

import ch.ivyteam.ivy.scripting.objects.File;
import java.util.List;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import deepl.translate.Options;

public class TranslateBodyBuilder {
	private TranslateBodyBuilder() {
	}

	private static final String SOURCE_LANG = "source_lang";
	private static final String TARGET_LANG = "target_lang";
	private static final String SPLIT_SENTENCES = "split_sentences";
	private static final String PRESERVE_FORMATTING = "preserve_formatting";
	private static final String FORMALITY = "formality";
	private static final String GLOSSARY_ID = "glossary_id";
	private static final String TAG_HANDLING = "tag_handling";
	private static final String NON_SPLITTING_TAGS = "non_splitting_tags";
	private static final String OUTLINE_DETECTION = "outline_detection";
	private static final String SPLITTING_TAGS = "splitting_tags";
	private static final String IGNORE_TAGS = "ignore_tags";
	private static final String FILE = "file";
	private static final String FILE_NAME = "filename";

	public static MultivaluedMap<String, String> buildFormBodyByOptions(Options options) {
		MultivaluedMap<String, String> body = new MultivaluedHashMap<>();
		putIfNotNull(body, SOURCE_LANG, options.getSourceLang());
		putIfNotNull(body, TARGET_LANG, options.getTargetLang());
		putIfNotNull(body, SPLIT_SENTENCES, options.getSplitSentences());
		putIfNotNull(body, PRESERVE_FORMATTING, options.getPreserveFormatting());
		putIfNotNull(body, FORMALITY, options.getFormality());
		putIfNotNull(body, GLOSSARY_ID, options.getGlossaryId());
		putIfNotNull(body, TAG_HANDLING, options.getTagHandling());
		putIfNotNull(body, NON_SPLITTING_TAGS, options.getNonSplittingTags());
		putIfNotNull(body, OUTLINE_DETECTION, options.getOutlineDetection());
		putIfNotNull(body, SPLITTING_TAGS, options.getSplittingTags());
		putIfNotNull(body, IGNORE_TAGS, options.getIgnoreTags());
		return body;
	}

	public static FormDataMultiPart buildMultiPartFormBodyByOptionsAndFile(Options options, File file) {
		FormDataMultiPart body = new FormDataMultiPart();
		putIfNotNull(body, SOURCE_LANG, options.getSourceLang());
		putIfNotNull(body, TARGET_LANG, options.getTargetLang());
		putIfNotNull(body, SPLIT_SENTENCES, options.getSplitSentences());
		putIfNotNull(body, PRESERVE_FORMATTING, options.getPreserveFormatting());
		putIfNotNull(body, FORMALITY, options.getFormality());
		putIfNotNull(body, GLOSSARY_ID, options.getGlossaryId());
		putIfNotNull(body, TAG_HANDLING, options.getTagHandling());
		putIfNotNull(body, NON_SPLITTING_TAGS, options.getNonSplittingTags());
		putIfNotNull(body, OUTLINE_DETECTION, options.getOutlineDetection());
		putIfNotNull(body, SPLITTING_TAGS, options.getSplittingTags());
		putIfNotNull(body, IGNORE_TAGS, options.getIgnoreTags());
		if (file != null) {
			body.bodyPart(new FileDataBodyPart(FILE, file.getJavaFile()));
			if (StringUtils.isNotBlank(file.getName())) {
				body.field(FILE_NAME, file.getName());
			}
		}
		return body;
	}

	private static void putIfNotNull(MultivaluedMap<String, String> map, String key, Object value) {
		if (map == null || key == null || value == null) {
			return;
		}
		map.put(key, List.of(String.valueOf(value)));
	}

	private static void putIfNotNull(FormDataMultiPart multipart, String name, Object value) {
		if (multipart == null || name == null || value == null) {
			return;
		}
		multipart.field(name, String.valueOf(value));
	}
}
