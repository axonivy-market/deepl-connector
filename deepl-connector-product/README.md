# DeepL Connector

The DeepL Connector brings DeepL's high-quality machine translation right into your Axon Ivy processes. Translate single text snippets or entire documents such as Word, PDF and PowerPoint files into dozens of languages without ever leaving your workflow.

Powered by the official [DeepL API](https://www.deepl.com/docs-api), the connector handles authentication, formatting and document handling for you, so you can focus on building great business processes.

**Key features**

- Translate plain text into your chosen target language directly from your Axon Ivy processes.
- Translate complete documents (DOCX, PDF, PPTX and more) and receive the translated file back automatically.
- Fine-tune results with options such as formality, source language, glossaries and tag handling.
- Let DeepL auto-detect the source language, or set it explicitly when you need full control.
- Preserve the original formatting while translating, so your documents stay ready to use.
- Connect securely with your DeepL API key, configured through a single Axon Ivy variable.

## Demo

The demo project shows the DeepL Connector in action with two ready-to-run examples: one for translating text and one for translating whole documents. Install the [DeepL Connector](https://market.axonivy.com/deepl-connector) from the Axon Ivy Market to try them yourself.

### Demo Workflows

##### Translate Text

1. Start the **Translate Text** demo from the process menu.
2. Enter the text you want to translate and pick a target language.

![Translate text demo](img/txtTranslateDemo.png)

3. Submit the form to send your text to DeepL.
4. Review the translated text shown right back in the dialog.

![Advanced text translation demo](img/txtTranslateAdvancedDemo.png)

##### Translate File

1. Start the **Translate File** demo from the process menu.
2. Upload a document (for example DOCX, PDF or PPTX) and choose your target language.

![Translate file demo](img/docTranslationDemo.png)

3. Submit to let DeepL translate the whole document.
4. Download the translated file once processing is complete.

![Advanced document translation demo](img/docTranslationAdvancedDemo.png)

## Setup

- **Roles:** Everybody (configured in config/roles.xml)
- **OpenAPI:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

Follow these steps to connect your Axon Ivy environment to DeepL:

1. Create a DeepL API account and generate an API authentication key (DeepL API Free or DeepL API Pro). The key usually ends with `:fx`. See the [DeepL authentication guide](https://www.deepl.com/docs-api/api-access/authentication/) for details.
2. Set the variable `com.axonivy.connector.deepl.authKey` to your DeepL API key. The key is treated as a password and should be kept secret.
3. The connector ships with a preconfigured REST client (`deepl-connector`) pointing to `https://api-free.deepl.com/v2`. If you use a DeepL API Pro account, change the URL to the Pro endpoint.
4. Run one of the demo processes to confirm that translation requests reach DeepL and that results are returned as expected.

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

The connector calls the DeepL REST API. The OpenAPI specification used by this connector is available here:

- **OpenAPI Spec URL:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

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
