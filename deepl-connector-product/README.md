# DeepL Connector

The DeepL Connector brings the power of [DeepL](https://www.deepl.com/), one of the world's most accurate machine translation services, straight into your Axon Ivy processes. Whether you need to translate a short piece of text or a complete document, the connector lets your applications speak your customers' language without any manual copy-and-paste work.

With a ready-to-use REST client and easy-to-call subprocesses, you can add high-quality, automated translations to your workflows in minutes. Just bring your own DeepL API key and let your processes communicate across language barriers.

**Key features**

- Translate text on the fly into a wide range of languages directly from your Axon Ivy processes.
- Translate whole documents such as Word, PDF, and PowerPoint files while keeping their original layout intact.
- Let DeepL detect the source language automatically or set it yourself for full control.
- Fine-tune every translation with options like formality, sentence splitting, and glossaries.
- Keep markup safe with built-in HTML and XML tag handling for formatted content.
- Connect securely using your own DeepL API key through a preconfigured REST client.

## Demo

Want to see the DeepL Connector in action? The bundled demo project ships with two ready-to-run dialogs that show how text and document translation work end to end. Launch them from the Axon Ivy Engine or Designer to explore the connector hands-on. Learn more about the underlying service in the [DeepL API documentation](https://www.deepl.com/docs-api).

### Demo Workflows

##### Translate Text

1. Launch the Translate Text process from the demo menu.
2. You'll see a form with an input field for your text and a selector for the target language.
3. Enter the text you want to translate, pick a target language, and optionally adjust advanced options like formality or tag handling.
4. Click translate and watch the translated result appear right next to your original text.

![Translate text demo dialog](img/txtTranslateDemo.png)

##### Translate File

1. Launch the Translate File process from the demo menu.
2. You'll see a form where you can upload a document (for example a Word, PDF, or PowerPoint file) and choose the target language.
3. Upload your file, select the language you want, and start the translation.
4. The connector uploads the document to DeepL, waits until it is processed, and provides the translated file for download with its formatting preserved.

![Translate file demo dialog](img/docTranslationDemo.png)

## Setup

To use the DeepL Connector, connect it to your DeepL account with a personal authentication key.

- **Roles:** Everybody (configured in `config/roles.xml`)
- **OpenAPI:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

1. Create a DeepL account and subscribe to a plan that fits your needs. You can start with the free API plan at [DeepL Pro](https://www.deepl.com/pro-api).
2. Open your DeepL account settings and copy your [API authentication key](https://www.deepl.com/docs-api/api-access/authentication/). Free keys end with `:fx`.
3. Provide the key to the connector by setting the `com.axonivy.connector.deepl.authKey` variable in your application configuration (see the variables block below).
4. Run any of the demo processes and verify in the Runtime Log that translation requests are sent successfully to DeepL.

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

- For this market extension we do not provide any dialog components.

### Web Services

- **OpenAPI specification:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

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
