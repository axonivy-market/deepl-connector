# DeepL Connector

The DeepL Connector integrates DeepL's machine translation services into Axon Ivy, allowing you to translate text and documents directly within your processes. It provides callable subprocesses for text and document translation, supports formality and glossary options, and returns translations as strings or files.

**Key features**

- Translate text within processes: Send text to DeepL and receive translations directly in your process.
- Translate documents: Upload and download translated document files (e.g., DOCX, PDF, PPTX) with formality and glossary support.
- Flexible API options: Configure target languages, formality, and tag handling for precise translations.
- Batch and streaming support: Handle multi-line text inputs and document uploads for larger translations.
- Seamless Axon Ivy integration: Use callable subprocesses (`text(...)`, `document(...)`) to add translations to workflows.
- Reliable REST client configuration: Easily switch endpoints or mock the DeepL service for testing.

## Demo

Check the demo implementations provided in the demo module: [deepl-connector-demo](../deepl-connector-demo).

### Demo Workflows

##### translateText

1. Launch the translateText demo from the demo menu.
2. Enter the text you want to translate into the dialog.
3. Click the Translate button to submit the text.
4. The translated text is displayed or returned to the calling process.

##### translateFile

1. Launch the translateFile demo from the demo menu.
2. Upload a document (e.g., DOCX, PDF, PPTX) using the file dialog.
3. Click Upload/Translate and wait for processing.
4. Download the translated file or confirm it is saved to the configured location.

## Setup

- **Roles:** Everybody (configured in config/roles.xml)
- **OpenAPI:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

### Variables

```
@variables.yaml@
```

1. Start the DeepL mock server (published and maintained by DeepLcom) either by [using docker](https://github.com/DeepLcom/deepl-mock?tab=readme-ov-file#using-docker) or by running it [manually](https://github.com/DeepLcom/deepl-mock?tab=readme-ov-file#manually), depending on your development workflow.
2. Use your File Manager to navigate into the `configuration` directory of your Axon.ivy Designer.
3. Create a file called `app.yaml`, add the following contents with an editor and replace `{deepl-test-instance-url}` with the url of your deepl instance (by default, it will be localhost:3000):

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

4. Run any startable Process in the Demo project and verify in the RuntimeLog view that requests are being sent to localhost rather than deepl.com.

## Components

### Callable Subprocesses

#### translate.p.json

- **Signature**: text(String text, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translation: String
    - Input:
        - `text` (String) — The text to translate
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) — The wished target language
    - Result:
        - `translation` (String)

- **Signature**: document(File file, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translated: File
    - Input:
        - `file` (File) — A file to translate (e.g. docx, pdf, pptx)
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) — The wished target language
    - Result:
        - `translated` (File)

- **Signature**: text(String text, deepl.translate.Options options) -> translation: String
    - Input:
        - `text` (String) — The text to translate
        - `options` (deepl.translate.Options) — Full options for rest client
    - Result:
        - `translation` (String)

- **Signature**: document(File file, deepl.translate.Options options) -> translated: File
    - Input:
        - `file` (File) — A file to translate (e.g. docx, pdf, pptx)
        - `options` (deepl.translate.Options) — Full options for rest client
    - Result:
        - `translated` (File)

### Dialog Components

- For this market extension we do not provide any dialog components.

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
