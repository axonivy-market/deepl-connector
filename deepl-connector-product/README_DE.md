# DeepL Connector

Der DeepL Connector integriert die maschinellen Übersetzungsdienste von DeepL in Axon Ivy und ermöglicht dir, Texte und Dokumente direkt in deinen Prozessen zu übersetzen. Er stellt aufrufbare Unterprozesse für Text- und Dokumentübersetzungen bereit, unterstützt Optionen wie Formalitätsstufen und Glossare und liefert Übersetzungen als Texte oder Dateien zurück.

**Wichtigste Funktionen**

- Texte in Prozessen übersetzen: Sende Text an DeepL und erhalte Übersetzungen direkt in deinem Prozess.
- Dokumente übersetzen: Lade Dokumente hoch und lade übersetzte Dateien herunter (z. B. DOCX, PDF, PPTX) mit Unterstützung für Formalitäts- und Glossareinstellungen.
- Flexible API-Optionen: Konfiguriere Zielsprachen, Formalität und Tag-Handling für präzise Übersetzungen.
- Batch- und Streaming-Unterstützung: Verarbeite mehrzeilige Texteingaben und Dateiuploads für umfangreichere Übersetzungen.
- Nahtlose Axon Ivy-Integration: Verwende aufrufbare Unterprozesse (`text(...)`, `document(...)`), um Übersetzungen in Workflows einzubinden.
- Zuverlässige REST-Client-Konfiguration: Wechsle einfach Endpunkte oder simuliere den DeepL-Dienst für Tests.

## Demo

Schau dir die Demo-Implementierungen im Demo-Modul an: [deepl-connector-demo](../deepl-connector-demo).

### Demo-Workflows

##### translateText

1. Starte die `translateText`-Demo über das Demo-Menü.
2. Gib den Text ein, den du übersetzen möchtest.
3. Klicke auf die Schaltfläche „Translate“, um den Text zu übermitteln.
4. Die übersetzte Ausgabe wird angezeigt oder an den aufrufenden Prozess zurückgegeben.

##### translateFile

1. Starte die `translateFile`-Demo über das Demo-Menü.
2. Lade ein Dokument hoch (z. B. DOCX, PDF, PPTX) über den Dateidialog.
3. Klicke auf „Upload/Translate“ und warte auf die Verarbeitung.
4. Lade die übersetzte Datei herunter oder bestätige, dass sie am konfigurierten Ort gespeichert wurde.

## Setup

- **Rollen:** Everybody (konfiguriert in config/roles.xml)
- **OpenAPI:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

### Variablen

```
@variables.yaml@
```

1. Starte den DeepL-Mock-Server (veröffentlicht und gepflegt von DeepLcom) entweder per Docker ([Anleitung](https://github.com/DeepLcom/deepl-mock?tab=readme-ov-file#using-docker)) oder manuell ([Anleitung](https://github.com/DeepLcom/deepl-mock?tab=readme-ov-file#manually)), je nach deinem Entwicklungsworkflow.
2. Öffne den Dateimanager und navigiere in das Verzeichnis `configuration` deines Axon.ivy Designer.
3. Erstelle eine Datei namens `app.yaml`, füge folgenden Inhalt ein und ersetze `{deepl-test-instance-url}` durch die URL deiner DeepL-Instanz (standardmäßig `localhost:3000`):

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

4. Führe einen startbaren Prozess im Demo-Projekt aus und prüfe im RuntimeLog, dass Anfragen an `localhost` statt an `deepl.com` gesendet werden.

## Komponenten

### Aufrufbare Unterprozesse

#### translate.p.json

- **Signature**: text(String text, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translation: String
    - Eingabe:
        - `text` (String) — Der zu übersetzende Text
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) — Gewünschte Zielsprache
    - Ergebnis:
        - `translation` (String)

- **Signature**: document(File file, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translated: File
    - Eingabe:
        - `file` (File) — Eine zu übersetzende Datei (z. B. docx, pdf, pptx)
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) — Gewünschte Zielsprache
    - Ergebnis:
        - `translated` (File)

- **Signature**: text(String text, deepl.translate.Options options) -> translation: String
    - Eingabe:
        - `text` (String) — Der zu übersetzende Text
        - `options` (deepl.translate.Options) — Vollständige Optionen für den REST-Client
    - Ergebnis:
        - `translation` (String)

- **Signature**: document(File file, deepl.translate.Options options) -> translated: File
    - Eingabe:
        - `file` (File) — Eine zu übersetzende Datei (z. B. docx, pdf, pptx)
        - `options` (deepl.translate.Options) — Vollständige Optionen für den REST-Client
    - Ergebnis:
        - `translated` (File)

### Dialogkomponenten

- Für diese Markterweiterung sind keine Dialogkomponenten vorgesehen.

### Web Services

- OpenAPI-Spezifikation: https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

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
