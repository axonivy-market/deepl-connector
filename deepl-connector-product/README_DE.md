# DeepL Connector

Der DeepL Connector bringt die Leistung von [DeepL](https://www.deepl.com/), einem der weltweit präzisesten maschinellen Übersetzungsdienste, direkt in deine Axon Ivy Prozesse. Egal ob du einen kurzen Text oder ein komplettes Dokument übersetzen möchtest – der Connector lässt deine Anwendungen die Sprache deiner Kunden sprechen, ganz ohne mühsames Kopieren und Einfügen.

Mit einem sofort einsatzbereiten REST-Client und einfach aufrufbaren Teilprozessen fügst du deinen Workflows in wenigen Minuten hochwertige, automatisierte Übersetzungen hinzu. Bring einfach deinen eigenen DeepL API-Schlüssel mit und lass deine Prozesse über Sprachgrenzen hinweg kommunizieren.

**Wichtigste Funktionen**

- Übersetze Texte im Handumdrehen in zahlreiche Sprachen – direkt aus deinen Axon Ivy Prozessen.
- Übersetze ganze Dokumente wie Word-, PDF- und PowerPoint-Dateien, während das ursprüngliche Layout erhalten bleibt.
- Lass DeepL die Ausgangssprache automatisch erkennen oder lege sie selbst fest, wenn du die volle Kontrolle willst.
- Stimme jede Übersetzung mit Optionen wie Förmlichkeit, Satztrennung und Glossaren genau ab.
- Halte Markup sicher dank integrierter HTML- und XML-Tag-Behandlung für formatierte Inhalte.
- Verbinde dich sicher mit deinem eigenen DeepL API-Schlüssel über einen vorkonfigurierten REST-Client.

## Demo

Möchtest du den DeepL Connector in Aktion sehen? Das mitgelieferte Demo-Projekt enthält zwei sofort lauffähige Dialoge, die zeigen, wie die Text- und Dokumentübersetzung von Anfang bis Ende funktioniert. Starte sie über die Axon Ivy Engine oder den Designer, um den Connector praktisch auszuprobieren. Mehr über den zugrunde liegenden Dienst erfährst du in der [DeepL API-Dokumentation](https://www.deepl.com/docs-api).

### Demo-Workflows

##### Text übersetzen

1. Starte den Prozess „Text übersetzen" aus dem Demo-Menü.
2. Du siehst ein Formular mit einem Eingabefeld für deinen Text und einer Auswahl für die Zielsprache.
3. Gib den zu übersetzenden Text ein, wähle eine Zielsprache und passe bei Bedarf erweiterte Optionen wie Förmlichkeit oder Tag-Behandlung an.
4. Klicke auf Übersetzen und beobachte, wie das übersetzte Ergebnis direkt neben deinem Originaltext erscheint.

![Demo-Dialog zur Textübersetzung](img/txtTranslateDemo.png)

##### Datei übersetzen

1. Starte den Prozess „Datei übersetzen" aus dem Demo-Menü.
2. Du siehst ein Formular, in dem du ein Dokument (zum Beispiel eine Word-, PDF- oder PowerPoint-Datei) hochladen und die Zielsprache wählen kannst.
3. Lade deine Datei hoch, wähle die gewünschte Sprache und starte die Übersetzung.
4. Der Connector lädt das Dokument zu DeepL hoch, wartet, bis es verarbeitet ist, und stellt die übersetzte Datei mit erhaltener Formatierung zum Download bereit.

![Demo-Dialog zur Dateiübersetzung](img/docTranslationDemo.png)

## Setup

Um den DeepL Connector zu nutzen, verbinde ihn über einen persönlichen Authentifizierungsschlüssel mit deinem DeepL-Konto.

- **Rollen:** Everybody (konfiguriert in `config/roles.xml`)
- **OpenAPI:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

1. Erstelle ein DeepL-Konto und wähle einen Tarif, der zu deinen Anforderungen passt. Du kannst mit dem kostenlosen API-Tarif unter [DeepL Pro](https://www.deepl.com/pro-api) starten.
2. Öffne deine DeepL-Kontoeinstellungen und kopiere deinen [API-Authentifizierungsschlüssel](https://www.deepl.com/docs-api/api-access/authentication/). Kostenlose Schlüssel enden mit `:fx`.
3. Übergib den Schlüssel an den Connector, indem du die Variable `com.axonivy.connector.deepl.authKey` in deiner Anwendungskonfiguration setzt (siehe den Variablenblock unten).
4. Führe einen der Demo-Prozesse aus und überprüfe im Runtime-Log, dass die Übersetzungsanfragen erfolgreich an DeepL gesendet werden.

### Variablen

```
@variables.yaml@
```

## Komponenten

### Aufrufbare Teilprozesse

#### translate.p.json

- **Signature**: text(String text, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translation: String
    - Eingabe:
        - `text` (String) - Der zu übersetzende Text
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) - Die gewünschte Zielsprache
    - Ergebnis:
        - `translation` (String)

- **Signature**: document(File file, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translated: File
    - Eingabe:
        - `file` (File) - Eine zu übersetzende Datei (z. B. docx, pdf, pptx)
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) - Die Sprache, in die übersetzt werden soll
    - Ergebnis:
        - `translated` (File)

- **Signature**: text(String text, deepl.translate.Options options) -> translation: String
    - Eingabe:
        - `text` (String) - Der zu übersetzende Text
        - `options` (deepl.translate.Options) - Vollständige Optionen für den REST-Client
    - Ergebnis:
        - `translation` (String)

- **Signature**: document(File file, deepl.translate.Options options) -> translated: File
    - Eingabe:
        - `file` (File) - Eine zu übersetzende Datei (z. B. docx, pdf, pptx)
        - `options` (deepl.translate.Options) - Vollständige Optionen für den REST-Client
    - Ergebnis:
        - `translated` (File)

### Dialog-Komponenten

- Für diese Markterweiterung stellen wir keine Dialog-Komponenten bereit.

### Webdienste

- **OpenAPI-Spezifikation:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

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
