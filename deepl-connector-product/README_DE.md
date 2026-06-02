# DeepL Connector

DeepL Connector integriert die Machine-Translation von DeepL in Axon Ivy-Prozesse und ermöglicht dir, Text und Dokumente direkt aus deinen Workflows zu übersetzen. Er unterstützt die Übersetzung von einfachem Text und verschiedenen Dokumentformaten (z. B. docx, pdf, pptx) und bietet flexible Optionen für Zielsprache, Formalitätsstufe und Glossar-Unterstützung.

**Wichtigste Funktionen**

- Übersetze Text direkt aus deinen Axon Ivy-Prozessen mit der bereitgestellten Integration.
- Übersetze Dokumente (docx, pdf, pptx) und lade die übersetzten Dateien aus der Prozessoberfläche herunter.
- Konfiguriere Übersetzungsoptionen (Zielsprache, Formalitätsstufe, Glossare) für konsistente Ergebnisse.
- Integriert mit der DeepL REST API über einen konfigurierbaren REST-Client und unterstützt einen Mock-Server für die Entwicklung.
- Enthält Demo-Workflows, mit denen du Text- und Dateiübersetzungen testen kannst.
- Bewahrt Formatierung und HTML-Tag-Behandlung, damit strukturierte Inhalte beim Übersetzen erhalten bleiben.

## Demo

Sieh dir die Demo-Implementierungen unter `deepl-connector-demo` an, um Text- und Dateiübersetzungen interaktiv auszuprobieren.

### Demo-Workflows

#### Translate Text

1. Starte die Demo "Translate Text" über das Demo-Menü.
2. Wähle Quell- und Zielsprache aus und gib den zu übersetzenden Text ein.
3. Klicke auf "Translate", um die Übersetzung zu starten.
4. Überprüfe den übersetzten Text in der Ausgabe.

![Demo: Textübersetzung](img/txtTranslateDemo.png)

#### Translate File

1. Starte die Demo "Translate File" über das Demo-Menü.
2. Lade ein Dokument hoch (z. B. docx, pdf, pptx, txt) oder nutze die Beispieldatei.
3. Klicke auf "Upload", um die Übersetzung zu starten, und warte auf die Verarbeitung.
4. Lade die übersetzte Datei herunter oder öffne sie über den bereitgestellten Link.

![Demo: Dateiübersetzung](img/docTranslationDemo.png)

## Einrichtung

- **Rollen:** Everybody (konfiguriert in config/roles.xml)
- **OpenAPI:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

### Variablen

```
@variables.yaml@
```

1. Konfiguriere den REST-Client `deepl-connector` in `deepl-connector/config/rest-clients.yaml` und setze `AUTH.deepLKey` auf deinen DeepL-API-Schlüssel oder den Entwicklungs-Mock-Key.

2. Für Entwicklungstests: Starte den DeepL-Mock-Server (siehe https://github.com/DeepLcom/deepl-mock). Beispielkonfiguration für die Designer-`app.yaml`:

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

3. Deploye die Connector-Artefakte in deine Axon Ivy-Instanz oder installiere das Paket, und starte dann die Demo-Prozesse, um zu prüfen, dass die Übersetzungen an den konfigurierten DeepL-Endpunkt gesendet werden.

## Komponenten

### Callable Subprocesses

#### translate.p.json

- **Signature**: text(String text, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translation: String
    - Input:
        - `text` (String) — Der zu übersetzende Text
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) — Die gewünschte Zielsprache
    - Result:
        - `translation` (String) — Der resultierende übersetzte Text

- **Signature**: document(File file, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translated: File
    - Input:
        - `file` (File) — Eine zu übersetzende Datei (z. B. docx, pdf, pptx)
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) — Die gewünschte Zielsprache
    - Result:
        - `translated` (File) — Die übersetzte Datei

- **Signature**: text(String text, deepl.translate.Options options) -> translation: String
    - Input:
        - `text` (String) — Der zu übersetzende Text
        - `options` (deepl.translate.Options) — Vollständige Optionen für den REST-Client
    - Result:
        - `translation` (String) — Der resultierende übersetzte Text

- **Signature**: document(File file, deepl.translate.Options options) -> translated: File
    - Input:
        - `file` (File) — Eine zu übersetzende Datei (z. B. docx, pdf, pptx)
        - `options` (deepl.translate.Options) — Vollständige Optionen für den REST-Client
    - Result:
        - `translated` (File) — Die übersetzte Datei

### Dialogkomponenten

#### translateText — Translate your text
- **Namespace:** com.axonivy.connector.deepl.demo.translateText
- **Component type:** Form dialog
- **Fields:** - (none)
- **Purpose:** Übersetze Text über einen Dialog, in dem Nutzer Sprachen wählen und Text eingeben

#### translateFile — Translate your file
- **Namespace:** com.axonivy.connector.deepl.demo.translateFile
- **Component type:** UI dialog
- **Fields:** - (none)
- **Purpose:** Lade ein Dokument hoch, starte die Übersetzung und lade das Ergebnis herunter oder sieh es dir an

### Web Services

- OpenAPI Spec: https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

### Maven-Artefakte

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
