Sag Sprachbarrieren Lebewohl in der Prozessautomatisierung mit dem Übersetzungsservice von DeepL, bereitgestellt über Axon Ivy. Der Konnektor lässt sich nahtlos in deine bestehenden Workflows integrieren und ermöglicht dir mehrsprachige Kommunikation.

## Wichtigste Funktionen

Dieses Marketplace-Element:

- Basiert auf der zukunftssicheren OpenAPI-Spezifikation.
- Übersetzt komplette Word (.docx), PowerPoint (.pptx), PDF (.pdf), Text (.txt) und HTML (.html) Dateien.
- Ermöglicht dir die Nutzung aller DeepL-API-Funktionen.
- Unterstützt dich mit einer Demo-Implementierung, um deinen Integrationsaufwand zu reduzieren.
- Ermöglicht es Low-Code-Anwendern, mehrsprachige Benutzeroberflächen bereitzustellen.
- Bietet erweiterte Übersetzungsoptionen (Quellsprache, Tag‑Handling, Formalitätsstufe) über das `deepl.translate.Options` Objekt.

## Demo

Das Produkt enthält Demo-Implementierungen, mit denen du Text- und Dokumentübersetzung schnell in deinen Prozessen ausprobieren kannst.

![deepl-doc-demo](img/docTranslationDemo.png)
![deepl-txt-demo](img/txtTranslateDemo.png)
![deepl-activity](img/deeplSubCallActivity.png)

### Demo-Workflows

#### DeepL-Demo (deepl-connector-demo)

##### Text übersetzen
1. Starte den "Text übersetzen"-Prozess über das Demo-Menü oder das Dashboard.
2. Es öffnet sich ein Dialog, in dem du freien Text in jeder Sprache eingeben kannst.
3. Wähle die Zielsprache und gegebenenfalls weitere Optionen und starte die Übersetzung.
4. Der übersetzte Text wird sofort im Interface angezeigt.
5. Optional: Exportiere oder kopiere das Ergebnis für die weitere Verarbeitung.

##### Datei übersetzen
1. Starte den "Datei übersetzen"-Prozess über das Demo-Menü oder das Dashboard.
2. Lade ein Dokument (z. B. .docx, .pptx, .pdf) über den Dateiauswahldialog hoch.
3. Wähle die Zielsprache und gegebenenfalls zusätzliche Optionen.
4. Starte die Übersetzung und warte, bis die Verarbeitung abgeschlossen ist.
5. Lade die übersetzte Datei über den bereitgestellten Link herunter.

## Einrichtung

- **Rollen:** Everybody (konfiguriert in config/roles.xml)
- **OpenAPI:** Spec: https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml (Namespace: com.deepl.api.v2.client)

### Variablen

```
@variables.yaml@
```

Hole dir ein kostenloses Entwicklerkonto unter https://www.deepl.com/pro#developer und kopiere den API-Schlüssel in `config/variables.yaml` unter `variables.com.axonivy.connector.deepl.authKey`.

## Komponenten

### Connector-Prozesse

#### translate.p.json

- **text(String text, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translation: String**
	- Eingabe:
		- `text` (String) — Der zu übersetzende Text
		- `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) — Gewünschte Zielsprache
	- Ergebnis:
		- `translation` (String) — 

- **document(File file, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translated: File**
	- Eingabe:
		- `file` (File) — Eine Datei zur Übersetzung (z. B. docx, pdf, pptx)
		- `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) — Die Zielsprache
	- Ergebnis:
		- `translated` (File) — 

- **text(String text, deepl.translate.Options options) -> translation: String**
	- Eingabe:
		- `text` (String) — Der zu übersetzende Text
		- `options` (deepl.translate.Options) — Vollständige Optionen für den Rest-Client
	- Ergebnis:
		- `translation` (String) — 

- **document(File file, deepl.translate.Options options) -> translated: File**
	- Eingabe:
		- `file` (File) — Eine Datei zur Übersetzung (z. B. docx, pdf, pptx)
		- `options` (deepl.translate.Options) — Vollständige Optionen für den Rest-Client
	- Ergebnis:
		- `translated` (File) — 

### Form-Komponenten

#### translateTextData — Enthält Eingabe- und Ausgabe-Felder für die Textübersetzungs-Demo
- **Namespace:** com.axonivy.connector.deepl.demo.translateText
- **Komponententyp:** Data Class
- **Felder:**
   - `data` (com.axonivy.connector.deepl.demo.Data) — 
   - `languages` (java.util.List<com.axonivy.connector.deepl.LanguageInfo.Lang>) — 
   - `sourceLanguages` (java.util.List<com.axonivy.connector.deepl.LanguageInfo.SourceLang>) — 
   - `translate` (com.axonivy.connector.deepl.LanguageInfo.Lang) — 
   - `source` (com.axonivy.connector.deepl.LanguageInfo.SourceLang) — 
   - `inputText` (String) — 
   - `outputText` (String) — 
- **Verwendung:** translateText-Dialog, Demo-Workflows
- **Zweck:** Hält Benutzereingaben und Spracheinstellungen für die Textübersetzung

#### translateFileData — Enthält Eingabe- und Ausgabe-Felder für die Dateiübersetzungs-Demo
- **Namespace:** com.axonivy.connector.deepl.demo.translateFile
- **Komponententyp:** Data Class
- **Felder:**
   - `showContent` (Boolean) — 
   - `filePath` (String) — 
   - `languages` (java.util.List<com.axonivy.connector.deepl.LanguageInfo.Lang>) — 
   - `sourceLanguages` (java.util.List<com.axonivy.connector.deepl.LanguageInfo.SourceLang>) — 
   - `translate` (com.axonivy.connector.deepl.LanguageInfo.Lang) — 
   - `source` (com.axonivy.connector.deepl.LanguageInfo.SourceLang) — 
   - `ivyFile` (File) — 
   - `file` (File) — 
   - `translated` (File) — 
   - `exampleFile` (File) — 
   - `options` (deepl.translate.Options) — 
- **Verwendung:** translateFile-Dialog, Demo-Workflows
- **Zweck:** Enthält Dateiauswahl, Zielsprache und Optionen für Dokumentübersetzungen

### Maven-Artefakte

1. deepl-connector

```xml
<dependency>
  <groupId>com.axonivy.connector.deepl</groupId>
  <artifactId>deepl-connector</artifactId>
  <version>@version@</version>
  <type>iar</type>
</dependency>
```

2. deepl-connector-demo

```xml
<dependency>
  <groupId>com.axonivy.connector.deepl</groupId>
  <artifactId>deepl-connector-demo</artifactId>
  <version>@version@</version>
  <type>iar</type>
</dependency>
```
