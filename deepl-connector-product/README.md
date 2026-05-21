Say goodbye to language barriers in process automation with Axon Ivy's translation service powered by DeepL. The connector seamlessly integrates into your existing workflows, allowing you to communicate easily regardless of the language.

## Key features

This marketplace item:

- Is based on the future-proof OpenAPI specification.
- Translates entire Word (.docx), PowerPoint (.pptx), PDF (.pdf), text (.txt), and HTML (.html) files.
- Gives you the power of all DeepL API features.
- Supports you with a demo implementation to reduce your integration effort.
- Enables low-code citizen developers to provide multi-lingual user interfaces.
- Provide advanced translation options (source language, tag handling, formality) via the `deepl.translate.Options` object.

## Demo

The product includes demo implementations to quickly try text and document translation in your processes.

![deepl-doc-demo](img/docTranslationDemo.png)
![deepl-txt-demo](img/txtTranslateDemo.png)
![deepl-activity](img/deeplSubCallActivity.png)

### Demo workflows

#### DeepL Demo (deepl-connector-demo)

##### Translate Text
1. Launch the Translate Text demo from the demo menu or dashboard.
2. A dialog appears where you can enter free-form text in any language.
3. Fill in the target language and any translation options, then start the translation.
4. The translated text is displayed immediately in the interface.
5. Optionally export or copy the result for further processing.

##### Translate File
1. Launch the Translate File demo from the demo menu or dashboard.
2. Upload a document (e.g., .docx, .pptx, .pdf) using the file picker.
3. Choose the target language and any additional options.
4. Start the translation and wait for processing to complete.
5. Download the translated file from the resulting link.

### Demo workflows

- Document translation (demo):
	1. Open the `translateFile` demo dialog.
 2. Upload a file (e.g., .docx, .pdf, .pptx).
 3. Choose the target language and optional `deepl.translate.Options` (formality, glossary, tag handling).
 4. Start translation and download the translated file when ready.

- Text translation (demo):
	1. Open the `translateText` demo dialog.
 2. Paste or enter the text to be translated.
 3. Select the target language and advanced options if needed.
 4. Trigger translation and copy or use the returned text in your workflow.

## Setup

- **Roles:** Everybody (configured in config/roles.xml)
- **OpenAPI:** Spec: https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml (Namespace: com.deepl.api.v2.client)

### Variables

```
@variables.yaml@
```

Get a [free developer account](https://www.deepl.com/pro#developer) from DeepL.com and copy the API key of your account into `config/variables.yaml` under `variables.com.axonivy.connector.deepl.authKey`.

## Components

### Connector processes

#### translate.p.json

- **text(String text, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translation: String**
	- Input:
		- `text` (String) — The text to translate
		- `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) — The wished target language
	- Result:
		- `translation` (String) — 

- **document(File file, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translated: File**
	- Input:
		- `file` (File) — A file to translate (e.g. docx, pdf, pptx)
		- `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) — The language to translate to
	- Result:
		- `translated` (File) — 

- **text(String text, deepl.translate.Options options) -> translation: String**
	- Input:
		- `text` (String) — The text to translate
		- `options` (deepl.translate.Options) — Full options for rest client
	- Result:
		- `translation` (String) — 

- **document(File file, deepl.translate.Options options) -> translated: File**
	- Input:
		- `file` (File) — A file to translate (e.g. docx, pdf, pptx)
		- `options` (deepl.translate.Options) — Full options for rest client
	- Result:
		- `translated` (File) — 

### Form components

#### translateTextData — Captures input and output fields for text translation demo
- **Namespace:** com.axonivy.connector.deepl.demo.translateText
- **Component type:** Data Class
- **Fields:**
   - `data` (com.axonivy.connector.deepl.demo.Data) — 
   - `languages` (java.util.List<com.axonivy.connector.deepl.LanguageInfo.Lang>) — 
   - `sourceLanguages` (java.util.List<com.axonivy.connector.deepl.LanguageInfo.SourceLang>) — 
   - `translate` (com.axonivy.connector.deepl.LanguageInfo.Lang) — 
   - `source` (com.axonivy.connector.deepl.LanguageInfo.SourceLang) — 
   - `inputText` (String) — 
   - `outputText` (String) — 
- **Where used:** translateText dialog, demo workflows
- **Purpose:** Holds user-entered text and language selection for the text translation demo

#### translateFileData — Captures input and output fields for file translation demo
- **Namespace:** com.axonivy.connector.deepl.demo.translateFile
- **Component type:** Data Class
- **Fields:**
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
- **Where used:** translateFile dialog, demo workflows
- **Purpose:** Captures file selection, target language, and options for document translations

### Maven artifacts

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
