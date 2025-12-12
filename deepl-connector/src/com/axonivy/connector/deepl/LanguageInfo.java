package com.axonivy.connector.deepl;

import java.util.List;
import java.util.Objects;

import com.deepl.api.v2.client.SourceLanguage;
import com.deepl.api.v2.client.TargetLanguage;

public class LanguageInfo {

  public static Lang of(TargetLanguage tLang) {
    return TARGET_LANGUAGES.stream()
      .filter(lang -> Objects.equals(lang.key(), tLang.getValue()))
      .findAny()
      .orElseGet(()->new Lang(tLang , ""));
  }
  
  public static SourceLang of(SourceLanguage sLang) {
	return SOURCE_LANGUAGES.stream()
	  .filter(lang -> Objects.equals(lang.key(), sLang.getValue()))
	  .findAny()
	  .orElseGet(() -> new SourceLang(sLang, ""));
  }


  public static List<Lang> all() {
    return TARGET_LANGUAGES;
  }
  
  public static List<SourceLang> allSourceLanguages() {
	return SOURCE_LANGUAGES;
  }


  private static List<Lang> TARGET_LANGUAGES = List.of(
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
  
  private static List<SourceLang> SOURCE_LANGUAGES = List.of(
    new SourceLang(SourceLanguage.BG, "Bulgarian"),
	new SourceLang(SourceLanguage.CS, "Czech"),
	new SourceLang(SourceLanguage.DA, "Danish"),
	new SourceLang(SourceLanguage.DE, "German"),
	new SourceLang(SourceLanguage.EL, "Greek"),
	new SourceLang(SourceLanguage.EN, "English"),
	new SourceLang(SourceLanguage.ES, "Spanish"),
	new SourceLang(SourceLanguage.ET, "Estonian"),
	new SourceLang(SourceLanguage.FI, "Finnish"),
	new SourceLang(SourceLanguage.FR, "French"),
	new SourceLang(SourceLanguage.HU, "Hungarian"),
	new SourceLang(SourceLanguage.ID, "Indonesian"),
	new SourceLang(SourceLanguage.IT, "Italian"),
	new SourceLang(SourceLanguage.JA, "Japanese"),
	new SourceLang(SourceLanguage.KO, "Korean"),
	new SourceLang(SourceLanguage.LT, "Lithuanian"),
	new SourceLang(SourceLanguage.LV, "Latvian"),
	new SourceLang(SourceLanguage.NB, "Norwegian", "Bokmål"),
	new SourceLang(SourceLanguage.NL, "Dutch"),
	new SourceLang(SourceLanguage.PL, "Polish"),
	new SourceLang(SourceLanguage.PT, "Portuguese"),
	new SourceLang(SourceLanguage.RO, "Romanian"),
	new SourceLang(SourceLanguage.RU, "Russian"),
	new SourceLang(SourceLanguage.SK, "Slovak"),
	new SourceLang(SourceLanguage.SL, "Slovenian"),
	new SourceLang(SourceLanguage.SV, "Swedish"),
	new SourceLang(SourceLanguage.TR, "Turkish"),
	new SourceLang(SourceLanguage.UK, "Ukrainian"),
	new SourceLang(SourceLanguage.ZH, "Chinese", "simplified"));
  
  public record Lang(TargetLanguage key, String name, String variant) {
	public Lang(TargetLanguage key, String name) {
	  this(key, name, null);
	}
    public String getVariant() {
      return variant(); // accessor for JSF
    }
  }
  
  public record SourceLang(SourceLanguage key, String name, String variant) {
    public SourceLang(SourceLanguage key, String name) {
      this(key, name, null);
    }

    public String getVariant() {
      return variant(); // accessor for JSF
    }
  }

}
