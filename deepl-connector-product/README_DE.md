# DeepL Connector

Der DeepL Connector integriert die branchenführende Übersetzungs-API von [DeepL](https://www.deepl.com) in deine Axon Ivy-Prozesse. Übersetze Texte und ganze Dokumente direkt aus deinen Workflows heraus – mit präziser Kontrolle über Formalität, Glossare und Spracherkennung, angetrieben von der offiziellen DeepL REST API.

![DeepL Callable Sub Aktivität](img/deeplSubCallActivity.png)

**Wichtigste Funktionen**

- Texte direkt aus deinen Axon Ivy-Prozessen in jede unterstützte Sprache übersetzen
- Vollständige Dokumente (DOCX, PDF, PPTX, HTML, TXT) hochladen und übersetzen lassen, ohne die Originalformatierung zu verlieren
- Übersetzungsverhalten mit erweiterten Optionen steuern: Formalität, Glossar-IDs, Satzaufteilung und Tag-Behandlung
- Ausgangssprache automatisch erkennen oder aus allen unterstützten DeepL-Ausgangssprachen wählen
- Einfache Zielsprachen-Kürzel oder das vollständige `Options`-Objekt für erweiterte Übersetzungsszenarien verwenden
- Die offizielle DeepL OpenAPI-Spezifikation für typsichere REST-Aufrufe auf Connector-Ebene nutzen

## Demo

Der DeepL Connector enthält zwei interaktive Demo-Prozesse, mit denen du die Text- und Dokumentübersetzung direkt im Axon Ivy Designer ausprobieren kannst. Für den Betrieb mit dem integrierten Mock-Server wird kein DeepL-Konto benötigt.

### Demo Workflows

##### Translate Text

1. Starte den **Translate Text**-Prozess aus dem Axon Ivy Demo-Menü.
2. Es erscheint ein Formular mit Quell- und Zielsprachenauswahl sowie einem Textfeld, das mit einem HTML-Beispielausschnitt vorausgefüllt ist.
3. Wähle deine gewünschte Zielsprache und gib den zu übersetzenden Text ein oder ändere ihn.
4. Sende das Formular ab, um die Übersetzung auszulösen, und prüfe das Ergebnis im Ausgabefeld.

![Demo Textübersetzung](img/txtTranslateDemo.png)

![Erweiterte Demo Textübersetzung](img/txtTranslateAdvancedDemo.png)

##### Translate File

1. Starte den **Translate File**-Prozess aus dem Axon Ivy Demo-Menü.
2. Es erscheint ein Datei-Upload-Formular mit Auswahlfeldern für Quell- und Zielsprache.
3. Lade ein Dokument in einem unterstützten Format (DOCX, PPTX, TXT, PDF oder HTML) hoch oder klicke auf **Example file**, um die integrierte Beispielrechnung zu verwenden.
4. Klicke auf **Upload**, um die Datei zur Übersetzung an DeepL zu senden, und lade das übersetzte Dokument herunter, sobald die Verarbeitung abgeschlossen ist.

![Demo Dokumentübersetzung](img/docTranslationDemo.png)

![Erweiterte Demo Dokumentübersetzung](img/docTranslationAdvancedDemo.png)

## Einrichtung

- **Rollen:** Everybody
- **OpenAPI:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

1. Hole dir einen DeepL API-Authentifizierungsschlüssel aus der [DeepL API-Dokumentation](https://www.deepl.com/docs-api/api-access/authentication/). Schlüssel für den Free-Tarif enden mit `:fx`.
2. Öffne die Axon Ivy-Anwendungskonfiguration und navigiere zum Abschnitt **Variables**.
3. Setze die Variable `com.axonivy.connector.deepl.authKey` auf deinen DeepL API-Authentifizierungsschlüssel.

### Variablen

```
@variables.yaml@
```

## Komponenten

### Aufrufbare Teilprozesse

#### translate.p.json

- **Signatur**: text(String text, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translation: String
    - Eingabe:
        - `text` (String) - Der zu übersetzende Text
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) - Die gewünschte Zielsprache
    - Ergebnis:
        - `translation` (String)

- **Signatur**: text(String text, deepl.translate.Options options) -> translation: String
    - Eingabe:
        - `text` (String) - Der zu übersetzende Text
        - `options` (deepl.translate.Options) - Vollständige Optionen für den REST-Client
    - Ergebnis:
        - `translation` (String)

- **Signatur**: document(File file, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translated: File
    - Eingabe:
        - `file` (File) - Eine zu übersetzende Datei (z. B. docx, pdf, pptx)
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) - Die Zielsprache
    - Ergebnis:
        - `translated` (File)

- **Signatur**: document(File file, deepl.translate.Options options) -> translated: File
    - Eingabe:
        - `file` (File) - Eine zu übersetzende Datei (z. B. docx, pdf, pptx)
        - `options` (deepl.translate.Options) - Vollständige Optionen für den REST-Client
    - Ergebnis:
        - `translated` (File)

### Dialogkomponenten

- Für diese Markterweiterung stellen wir keine Dialogkomponenten bereit.

### Webservices

- Für diese Markterweiterung stellen wir keine Webservices bereit.

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
