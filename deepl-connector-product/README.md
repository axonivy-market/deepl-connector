# DeepL Connector

Speed up your business processes with high-quality machine translation. The DeepL Connector for Axon Ivy brings the industry-leading translation technology of [DeepL](https://www.deepl.com) directly into your workflows: translate plain text or complete documents into dozens of languages with a single subprocess call — no custom API integration required.

![DeepL Sub Call Activity](img/deeplSubCallActivity.png)

Learn more in the official [DeepL API documentation](https://www.deepl.com/docs-api).

**Key features**

- Translate plain text into all languages supported by DeepL directly from your Axon Ivy processes
- Translate complete documents (docx, pptx, txt, pdf, html) while preserving their layout and formatting
- Automatic source-language detection — just provide the text and pick a target language
- Fine-grained control via an options object: formality, glossaries, tag handling, sentence splitting and more
- Ready-to-use REST client preconfigured for the DeepL API — only your authentication key is required
- Demo dialogs included to try text and document translation right away

## Demo

The demo project shows how easily DeepL translations integrate into your applications: two ready-to-run dialogs let you translate free text and complete documents using the connector's callable subprocesses.

### Demo Workflows

##### Translate Text

1. Launch the Translate Text process from the demo overview.
2. You'll see a translation form: pick a source language or keep automatic language detection, then choose your target language.

   ![Translate Text Demo](img/txtTranslateDemo.png)

3. Enter your text in the input area; optionally set Tag Handling (for example `html`) to translate markup content correctly.
4. Click the translate button and review the translated text displayed next to your input.

##### Translate File

1. Launch the Translate File process from the demo overview.
2. Select the source language (or keep automatic detection) and the target language.

   ![Translate File Demo](img/docTranslationDemo.png)

3. Select a file (.docx, .pptx, .txt, .pdf, .html) and click Upload — or simply try the provided example file.
4. Wait a moment while DeepL translates the document, then download the translated file from the result view.

## Setup

- **Roles:** Everybody (configured in config/roles.xml)
- **OpenAPI:** [DeepL API specification](https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml)

1. Create a DeepL account and request an API authentication key (see [DeepL API authentication](https://www.deepl.com/docs-api/api-access/authentication/)).
2. Store the key in the variable `com.axonivy.connector.deepl.authKey`. For the free plan the key ends with `:fx`.
3. If you are on a paid DeepL plan, adjust the URL of the REST client `deepl-connector` (default: `https://api-free.deepl.com/v2`) to the pro endpoint of the DeepL API.

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

- **Signature**: text(String text, deepl.translate.Options options) -> translation: String
    - Input:
        - `text` (String) - The text to translate
        - `options` (deepl.translate.Options) - Full options for rest client
    - Result:
        - `translation` (String)

- **Signature**: document(File file, deepl.translate.Options options) -> translated: File
    - Input:
        - `file` (File) - A file to translate (e.g. docx, pdf, pptx)
        - `options` (deepl.translate.Options) - Full options for rest client
    - Result:
        - `translated` (File)

### Dialog Components

- For this market extension we do not provide any Dialog Components.

### Web Services

- **OpenAPI:** [DeepL API specification](https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml)

### Maven Artifacts

1. deepl-connector

```xml
<dependency>
  <groupId>com.axonivy.connector.deepl</groupId>
  <artifactId>deepl-connector</artifactId>
  <type>iar</type>
</dependency>
```

2. deepl-connector-demo

```xml
<dependency>
  <groupId>com.axonivy.connector.deepl</groupId>
  <artifactId>deepl-connector-demo</artifactId>
  <type>iar</type>
</dependency>
```
