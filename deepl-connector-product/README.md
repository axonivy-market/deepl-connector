# DeepL Connector

DeepL Connector integrates DeepL's machine translation into Axon Ivy processes, enabling you to translate text and documents directly from your workflows. It supports translating plain text and a range of document formats (e.g., docx, pdf, pptx) and provides flexible options for target language, formality, and glossary support.

**Key features**

- Translate text directly from your Axon Ivy processes using the provided integration.
- Translate documents (docx, pdf, pptx) and download translated files from the process UI.
- Configure translation options (target language, formality, glossaries) for consistent results.
- Integrates with the DeepL REST API via a configurable REST client and supports a mock server for development.
- Includes demo workflows to try text and file translation in the included demo module.
- Preserve formatting and HTML tag-handling options to keep structured content intact during translation.

## Demo

Check the demo implementations under `deepl-connector-demo` to try text and file translation workflows interactively.

### Demo Workflows

#### Translate Text

1. Launch the "Translate Text" demo from the demo menu.
2. Choose source and target language and enter the text you want to translate.
3. Click the "Translate" button to start the translation.
4. Review the translated text in the output area.

![Translate Text Demo](img/txtTranslateDemo.png)

#### Translate File

1. Launch the "Translate File" demo from the demo menu.
2. Upload a document (docx, pdf, pptx, txt) or use the example file.
3. Click "Upload" to start the translation and wait for processing.
4. Download or open the translated file from the provided link in the demo.

![Document Translation Demo](img/docTranslationDemo.png)

## Setup

- **Roles:** Everybody (configured in config/roles.xml)
- **OpenAPI:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

### Variables

```
@variables.yaml@
```

1. Configure the `deepl-connector` REST client in `deepl-connector/config/rest-clients.yaml` and set `AUTH.deepLKey` to your DeepL API key or the development mock key.

2. For development testing, start the DeepL mock server (see https://github.com/DeepLcom/deepl-mock). Example configuration to add to your Designer `app.yaml`:

```yaml
# yaml-language-server: $schema=https://json-schema.axonivy.com/app/12.0.0/app.json
RestClients:
  deepl-connector:
    Url: {deepl-test-instance-url}
    Properties:
      AUTH.deepLKey: notMyKey
    Features:
    - ch.ivyteam.ivy.rest.client.mapper.JsonFeature
    - org.glassfish.jersey.media.multipart.MultiPartFeature
    - ch.ivyteam.ivy.rest.client.security.CsrfHeaderFeature
```

3. Deploy the connector artifacts into your Axon Ivy instance or install the packaged product, then run the demo processes to verify translations reach your configured DeepL endpoint.

## Components

### Callable Subprocesses

#### translate.p.json

- **Signature**: text(String text, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translation: String
    - Input:
        - `text` (String) — The text to translate
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) — The wished target language
    - Result:
        - `translation` (String) — The resulting translated text

- **Signature**: document(File file, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translated: File
    - Input:
        - `file` (File) — A file to translate (e.g. docx, pdf, pptx)
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) — The wished target language
    - Result:
        - `translated` (File) — The translated file

- **Signature**: text(String text, deepl.translate.Options options) -> translation: String
    - Input:
        - `text` (String) — The text to translate
        - `options` (deepl.translate.Options) — Full options for rest client
    - Result:
        - `translation` (String) — The resulting translated text

- **Signature**: document(File file, deepl.translate.Options options) -> translated: File
    - Input:
        - `file` (File) — A file to translate (e.g. docx, pdf, pptx)
        - `options` (deepl.translate.Options) — Full options for rest client
    - Result:
        - `translated` (File) — The translated file

### Dialog Components

#### translateText — Translate your text
- **Namespace:** com.axonivy.connector.deepl.demo.translateText
- **Component type:** Form dialog
- **Fields:** - (none)
- **Purpose:** Translate text via a dialog where users select languages and input text for translation

#### translateFile — Translate your file
- **Namespace:** com.axonivy.connector.deepl.demo.translateFile
- **Component type:** UI dialog
- **Fields:** - (none)
- **Purpose:** Upload a document, start translation, and download or view the translated output

### Web Services

- OpenAPI Spec: https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

### Maven Artifacts

1. deepl-connector

```xml
<dependency>
  <groupId>com.axonivy.connector.deepl</groupId>
  <artifactId>deepl-connector</artifactId>
  <type>iar</type>
</dependency>
```

2. deepl-connector-demo *(optional)*

```xml
<dependency>
  <groupId>com.axonivy.connector.deepl</groupId>
  <artifactId>deepl-connector-demo</artifactId>
  <type>iar</type>
</dependency>
```
