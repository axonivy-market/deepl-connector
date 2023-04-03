package com.axonivy.connector.deepl;

import java.util.List;

import com.deepl.api.v2.client.TargetLanguage;
import com.google.common.base.Objects;

public class LanguageInfo {

  public static Lang of(TargetLanguage tLang) {
    return LANGUAGES.stream()
      .filter(lang -> Objects.equal(lang.key(), tLang.getValue()))
      .findAny()
      .orElseGet(()->new Lang(tLang , ""));
  }

  public static List<Lang> all() {
    return LANGUAGES;
  }

  private static List<Lang> LANGUAGES = List.of(
    new Lang(TargetLanguage.BG, "Bulgarian"),
    new Lang(TargetLanguage.CS, "Czech"),
    new Lang(TargetLanguage.DA, "Danish"),
    new Lang(TargetLanguage.DE, "German"),
    new Lang(TargetLanguage.EL, "Greek"),
    new Lang(TargetLanguage.EN_GB, "English", "British"),
    new Lang(TargetLanguage.EN_US, "English", "American"),
    new Lang(TargetLanguage.ES, "Spanish"),
    new Lang(TargetLanguage.ET, "Estonian"),
    new Lang(TargetLanguage.FI, "Finnish"),
    new Lang(TargetLanguage.FR, "French"),
    new Lang(TargetLanguage.HU, "Hungarian"),
    new Lang(TargetLanguage.ID, "Indonesian"),
    new Lang(TargetLanguage.IT, "Italian"),
    new Lang(TargetLanguage.JA, "Japanese"),
    new Lang(TargetLanguage.KO, "Korean"),
    new Lang(TargetLanguage.LT, "Lithuanian"),
    new Lang(TargetLanguage.LV, "Latvian"),
    new Lang(TargetLanguage.NB, "Norwegian", "Bokmål"),
    new Lang(TargetLanguage.NL, "Dutch"),
    new Lang(TargetLanguage.PL, "Polish"),
    new Lang(TargetLanguage.PT_BR, "Portuguese", "Brazilian"),
    new Lang(TargetLanguage.PT_PT, "Portuguese", "all Portuguese varieties excluding Brazilian Portuguese"),
    new Lang(TargetLanguage.RO, "Romanian"),
    new Lang(TargetLanguage.RU, "Russian"),
    new Lang(TargetLanguage.SK, "Slovak"),
    new Lang(TargetLanguage.SL, "Slovenian"),
    new Lang(TargetLanguage.SV, "Swedish"),
    new Lang(TargetLanguage.TR, "Turkish"),
    new Lang(TargetLanguage.UK, "Ukrainian"),
    new Lang(TargetLanguage.ZH, "Chinese", "simplified"));
  
  public record Lang(TargetLanguage key, String name, String variant) {
	  
    public Lang(TargetLanguage key, String name) {
      this(key, name, null);
    }

    public String getVariant() {
      return variant(); // accessor for JSF
    }
  }

}
