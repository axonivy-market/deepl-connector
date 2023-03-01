package com.axonivy.connector.deepl;

import java.util.List;

import com.deepl.api.v2.client.TargetLanguage;
import com.google.common.base.Objects;

public class LanguageInfo {

  public static Lang of(TargetLanguage tLang) {
    return LANGUAGES.stream()
      .filter(lang -> Objects.equal(lang.key(), tLang.getValue()))
      .findAny()
      .orElseGet(()->new Lang(tLang.getValue(), ""));
  }

  public static List<Lang> all() {
    return LANGUAGES;
  }

  private static List<Lang> LANGUAGES = List.of(
    new Lang("BG", "Bulgarian"),
    new Lang("CS", "Czech"),
    new Lang("DA", "Danish"),
    new Lang("DE", "German"),
    new Lang("EL", "Greek"),
    new Lang("EN", "English",
            "unspecified variant for backward compatibility; please select `EN-GB` or `EN-US` instead"),
    new Lang("EN-GB", "English", "British"),
    new Lang("EN-US", "English", "American"),
    new Lang("ES", "Spanish"),
    new Lang("ET", "Estonian"),
    new Lang("FI", "Finnish"),
    new Lang("FR", "French"),
    new Lang("HU", "Hungarian"),
    new Lang("ID", "Indonesian"),
    new Lang("IT", "Italian"),
    new Lang("JA", "Japanese"),
    new Lang("KO", "Korean"),
    new Lang("LT", "Lithuanian"),
    new Lang("LV", "Latvian"),
    new Lang("NB", "Norwegian", "Bokmål"),
    new Lang("NL", "Dutch"),
    new Lang("PL", "Polish"),
    new Lang("PT", "Portuguese",
            "unspecified variant for backward compatibility; please select `PT-BR` or `PT-PT` instead"),
    new Lang("PT-BR", "Portuguese", "Brazilian"),
    new Lang("PT-PT", "Portuguese", "all Portuguese varieties excluding Brazilian Portuguese"),
    new Lang("RO", "Romanian"),
    new Lang("RU", "Russian"),
    new Lang("SK", "Slovak"),
    new Lang("SL", "Slovenian"),
    new Lang("SV", "Swedish"),
    new Lang("TR", "Turkish"),
    new Lang("UK", "Ukrainian"),
    new Lang("ZH", "Chinese", "simplified"));

  public record Lang(String key, String name, String variant) {

    public Lang(String key, String name) {
      this(key, name, null);
    }

    public String getVariant() {
      return variant(); // accessor for JSF
    }
  }

}
