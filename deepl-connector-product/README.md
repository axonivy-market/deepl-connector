# DeepL Connector

The DeepL Connector integrates the powerful [DeepL translation API](https://www.deepl.com/docs-api/) with Axon Ivy, enabling seamless, high-quality machine translation directly within your business processes. Whether you need to translate a short text snippet or a complete document, the connector handles it all — powered by one of the world's most accurate translation engines.

![DeepL Callable Sub Activity](img/deeplSubCallActivity.png)

**Key features**

- Translate text strings from any source language into any DeepL-supported target language directly from your processes
- Translate full documents (DOCX, PDF, PPTX, HTML, TXT) and receive the translated file as output
- Auto-detect the source language or specify it explicitly for full control over the translation
- Customize translation behavior with formality level, tag handling, sentence splitting, and glossary settings
- Authenticate securely using a configurable DeepL API key stored as an Axon Ivy variable
- Explore ready-to-use demo workflows for both text and document translation

## Demo

Try the included demo workflows to see the DeepL Connector in action. The demos show how to translate a text snippet and how to upload and translate a full document using the DeepL API.

### Demo Workflows

##### Translate Text

1. Launch the Translate Text demo from the demo menu.
2. You'll see a form with a text input area, source language selector, and target language selector.

![Translate Text Demo](img/txtTranslateDemo.png)

3. Enter or modify the sample text in the input field (the default is `<h1>Hello world</h1>`).
4. Select your desired target language and optionally specify a source language — leaving it empty enables automatic language detection.
5. Click Translate — the translated text appears immediately in the output area below.

##### Translate File

1. Launch the Translate File demo from the demo menu.
2. You'll see an upload form where you can select a document file (DOCX, PDF, PPTX, TXT, or HTML).
3. Select source and target languages, then upload your file or try the provided example invoice.

![Translate File Demo](img/docTranslationDemo.png)

4. Click Upload — the connector sends your document to DeepL and polls until translation is complete.
5. Once finished, a download link appears so you can retrieve the translated file directly from the result panel.

## Setup

- **Roles:** Everybody (configured in config/roles.xml)
- **OpenAPI:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

1. Sign up for a [DeepL API account](https://www.deepl.com/pro-api) and obtain your authentication key (free-tier keys end with `:fx`).
2. In your Axon Ivy project, set the variable `com.axonivy.connector.deepl.authKey` to your DeepL API key.
3. The connector targets the DeepL free API endpoint (`https://api-free.deepl.com/v2`) by default. For paid accounts, update the `Url` in the `deepl-connector` REST client configuration to `https://api.deepl.com/v2`.
4. Verify the setup by running one of the demo workflows and checking for a successful translation response.

### Variables

```
@variables.yaml@
```

## Components

### Callable Subprocesses

#### translate.p.json

- **Signature**: text(String text, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translation: String
    - Input:
        - `text` (String) - The text to translate
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) - The wished target language
    - Result:
        - `translation` (String)

- **Signature**: document(File file, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translated: File
    - Input:
        - `file` (File) - A file to translate (e.g. docx, pdf, pptx)
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) - The language to translate to
    - Result:
        - `translated` (File)

### Dialog Components

#### translateFile — Upload and translate a document file

- **Namespace:** com.axonivy.connector.deepl.demo.translateFile
- **Component type:** UI dialog
- **Fields:** - (none)
- **Purpose:** Allows users to upload a document, select source and target languages, and download the translated result file.

#### translateText — Translate a text string

- **Namespace:** com.axonivy.connector.deepl.demo.translateText
- **Component type:** Form dialog
- **Fields:** - (none)
- **Purpose:** Provides a simple form to enter text, choose source and target languages, and view the translation result inline.

### Web Services

- **OpenAPI Spec URL:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

### Maven Artifacts

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
