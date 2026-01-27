package com.axonivy.connector.deepl;

import ch.ivyteam.ivy.scripting.objects.File;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import deepl.translate.Options;

public final class TranslateBodyBuilder {
  private static final String FILE = "file";
  private static final String FILE_NAME = "filename";

  private TranslateBodyBuilder() {
  }

  private enum Field {
    SOURCE_LANG("source_lang"), TARGET_LANG("target_lang"), SPLIT_SENTENCES("split_sentences"),
    PRESERVE_FORMATTING("preserve_formatting"), FORMALITY("formality"), GLOSSARY_ID("glossary_id"),
    TAG_HANDLING("tag_handling"), NON_SPLITTING_TAGS("non_splitting_tags"), OUTLINE_DETECTION("outline_detection"),
    SPLITTING_TAGS("splitting_tags"), IGNORE_TAGS("ignore_tags");

    private final String fieldName;

    Field(String fieldName) {
      this.fieldName = fieldName;
    }

    public String fieldName() {
      return fieldName;
    }
  }

  public static MultivaluedMap<String, String> buildFormBodyByOptions(Options options) {
    MultivaluedMap<String, String> body = new MultivaluedHashMap<>();
    forEachOption(options, (key, value) -> body.put(key, List.of(String.valueOf(value))));
    return body;
  }

  public static FormDataMultiPart buildMultiPartFormBodyByOptionsAndFile(Options options, File file) {
    FormDataMultiPart body = new FormDataMultiPart();
    forEachOption(options, body::field);
    if (file != null) {
      body.bodyPart(new FileDataBodyPart(FILE, file.getJavaFile()));
      if (StringUtils.isNotBlank(file.getName())) {
        body.field(FILE_NAME, file.getName());
      }
    }
    return body;
  }

  private static void forEachOption(Options options, BiConsumer<String, String> consumer) {
    if (options == null || consumer == null) {
      return;
    }
    extractOptions(options).forEach((field, value) -> {
      if (value != null) {
        consumer.accept(field.fieldName(), String.valueOf(value));
      }
    });
  }

  private static Map<Field, Object> extractOptions(Options options) {
    Map<Field, Object> map = new EnumMap<>(Field.class);
    map.put(Field.SOURCE_LANG, options.getSourceLang());
    map.put(Field.TARGET_LANG, options.getTargetLang());
    map.put(Field.SPLIT_SENTENCES, options.getSplitSentences());
    map.put(Field.PRESERVE_FORMATTING, options.getPreserveFormatting());
    map.put(Field.FORMALITY, options.getFormality());
    map.put(Field.GLOSSARY_ID, options.getGlossaryId());
    map.put(Field.TAG_HANDLING, options.getTagHandling());
    map.put(Field.NON_SPLITTING_TAGS, options.getNonSplittingTags());
    map.put(Field.OUTLINE_DETECTION, options.getOutlineDetection());
    map.put(Field.SPLITTING_TAGS, options.getSplittingTags());
    map.put(Field.IGNORE_TAGS, options.getIgnoreTags());
    return map;
  }
}
