# DeepL Connector

Beschleunige deine Geschäftsprozesse mit hochwertiger maschineller Übersetzung. Der DeepL Connector für Axon Ivy bringt die branchenführende Übersetzungstechnologie von [DeepL](https://www.deepl.com) direkt in deine Workflows: Übersetze einfachen Text oder komplette Dokumente mit einem einzigen Subprozess-Aufruf in Dutzende Sprachen — ganz ohne eigene API-Integration.

![DeepL-Subprozess-Aufruf](img/deeplSubCallActivity.png)

Mehr erfährst du in der offiziellen [DeepL API-Dokumentation](https://www.deepl.com/docs-api).

**Wichtigste Funktionen**

- Übersetze einfachen Text direkt aus deinen Axon Ivy Prozessen in alle von DeepL unterstützten Sprachen
- Übersetze komplette Dokumente (docx, pptx, txt, pdf, html), wobei Layout und Formatierung erhalten bleiben
- Automatische Erkennung der Ausgangssprache — gib einfach den Text ein und wähle eine Zielsprache
- Feinsteuerung über ein Options-Objekt: Formalität, Glossare, Tag-Handling, Satztrennung und mehr
- Sofort einsatzbereiter REST-Client, vorkonfiguriert für die DeepL API — du brauchst nur deinen Authentifizierungsschlüssel
- Demo-Dialoge inklusive, mit denen du Text- und Dokumentübersetzung direkt ausprobieren kannst

## Demo

Das Demo-Projekt zeigt, wie einfach sich DeepL-Übersetzungen in deine Anwendungen integrieren lassen: Zwei sofort startbare Dialoge lassen dich freien Text und komplette Dokumente über die aufrufbaren Subprozesse des Connectors übersetzen.

### Demo-Workflows

##### Text übersetzen

1. Starte den Prozess Translate Text aus der Demo-Übersicht.
2. Du siehst ein Übersetzungsformular: Wähle eine Ausgangssprache oder behalte die automatische Spracherkennung bei und wähle anschließend deine Zielsprache.

   ![Text übersetzen Demo](img/txtTranslateDemo.png)

3. Gib deinen Text in das Eingabefeld ein; optional kannst du Tag Handling setzen (zum Beispiel `html`), um Markup-Inhalte korrekt zu übersetzen.
4. Klicke auf die Übersetzen-Schaltfläche und prüfe den übersetzten Text, der neben deiner Eingabe angezeigt wird.

##### Datei übersetzen

1. Starte den Prozess Translate File aus der Demo-Übersicht.
2. Wähle die Ausgangssprache (oder behalte die automatische Erkennung bei) und die Zielsprache.

   ![Datei übersetzen Demo](img/docTranslationDemo.png)

3. Wähle eine Datei aus (.docx, .pptx, .txt, .pdf, .html) und klicke auf Upload — oder probiere einfach die mitgelieferte Beispieldatei aus.
4. Warte einen Moment, während DeepL das Dokument übersetzt, und lade anschließend die übersetzte Datei aus der Ergebnisansicht herunter.

## Einrichtung

- **Rollen:** Everybody (konfiguriert in config/roles.xml)
- **OpenAPI:** [DeepL API-Spezifikation](https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml)

1. Erstelle ein DeepL-Konto und fordere einen API-Authentifizierungsschlüssel an (siehe [DeepL API-Authentifizierung](https://www.deepl.com/docs-api/api-access/authentication/)).
2. Hinterlege den Schlüssel in der Variable `com.axonivy.connector.deepl.authKey`. Beim Free-Plan endet der Schlüssel mit `:fx`.
3. Wenn du einen kostenpflichtigen DeepL-Plan nutzt, passe die URL des REST-Clients `deepl-connector` (Standard: `https://api-free.deepl.com/v2`) auf den Pro-Endpunkt der DeepL API an.

### Variablen

```
@variables.yaml@
```

## Komponenten

### Aufrufbare Subprozesse

#### translate.p.json

- **Signatur**: text(String text, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translation: String
    - Eingabe:
        - `text` (String) - Der zu übersetzende Text
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) - Die gewünschte Zielsprache
    - Ergebnis:
        - `translation` (String)

- **Signatur**: document(File file, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translated: File
    - Eingabe:
        - `file` (File) - Eine zu übersetzende Datei (z. B. docx, pdf, pptx)
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) - Die Sprache, in die übersetzt werden soll
    - Ergebnis:
        - `translated` (File)

- **Signatur**: text(String text, deepl.translate.Options options) -> translation: String
    - Eingabe:
        - `text` (String) - Der zu übersetzende Text
        - `options` (deepl.translate.Options) - Vollständige Optionen für den REST-Client
    - Ergebnis:
        - `translation` (String)

- **Signatur**: document(File file, deepl.translate.Options options) -> translated: File
    - Eingabe:
        - `file` (File) - Eine zu übersetzende Datei (z. B. docx, pdf, pptx)
        - `options` (deepl.translate.Options) - Vollständige Optionen für den REST-Client
    - Ergebnis:
        - `translated` (File)

### Dialog-Komponenten

- Für diese Markterweiterung stellen wir keine Dialog-Komponenten bereit.

### Webdienste

- **OpenAPI:** [DeepL API-Spezifikation](https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml)

### Maven-Artefakte

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
