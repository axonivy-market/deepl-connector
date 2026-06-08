# DeepL Connector

Der DeepL Connector bringt die hochwertige maschinelle Übersetzung von DeepL direkt in deine Axon Ivy Prozesse. Übersetze einzelne Textbausteine oder ganze Dokumente wie Word-, PDF- und PowerPoint-Dateien in Dutzende Sprachen, ohne deinen Workflow zu verlassen.

Angetrieben von der offiziellen [DeepL API](https://www.deepl.com/docs-api) übernimmt der Connector Authentifizierung, Formatierung und Dokumentenverarbeitung für dich, damit du dich auf den Aufbau großartiger Geschäftsprozesse konzentrieren kannst.

**Wichtigste Funktionen**

- Übersetze einfachen Text direkt aus deinen Axon Ivy Prozessen in deine gewünschte Zielsprache.
- Übersetze komplette Dokumente (DOCX, PDF, PPTX und mehr) und erhalte die übersetzte Datei automatisch zurück.
- Verfeinere die Ergebnisse mit Optionen wie Förmlichkeit, Ausgangssprache, Glossaren und Tag-Verarbeitung.
- Lass DeepL die Ausgangssprache automatisch erkennen oder lege sie explizit fest, wenn du volle Kontrolle brauchst.
- Behalte die ursprüngliche Formatierung beim Übersetzen bei, sodass deine Dokumente einsatzbereit bleiben.
- Verbinde dich sicher mit deinem DeepL API-Schlüssel, der über eine einzige Axon Ivy Variable konfiguriert wird.

## Demo

Das Demo-Projekt zeigt den DeepL Connector in Aktion mit zwei sofort einsatzbereiten Beispielen: eines zum Übersetzen von Text und eines zum Übersetzen ganzer Dokumente. Installiere den [DeepL Connector](https://market.axonivy.com/deepl-connector) aus dem Axon Ivy Market, um sie selbst auszuprobieren.

### Demo-Workflows

##### Text übersetzen

1. Starte die Demo **Text übersetzen** aus dem Prozessmenü.
2. Gib den Text ein, den du übersetzen möchtest, und wähle eine Zielsprache.

![Demo zur Textübersetzung](img/txtTranslateDemo.png)

3. Sende das Formular ab, um deinen Text an DeepL zu übermitteln.
4. Sieh dir den übersetzten Text direkt im Dialog an.

![Erweiterte Demo zur Textübersetzung](img/txtTranslateAdvancedDemo.png)

##### Datei übersetzen

1. Starte die Demo **Datei übersetzen** aus dem Prozessmenü.
2. Lade ein Dokument hoch (zum Beispiel DOCX, PDF oder PPTX) und wähle deine Zielsprache.

![Demo zur Dokumentübersetzung](img/docTranslationDemo.png)

3. Sende das Formular ab, damit DeepL das gesamte Dokument übersetzt.
4. Lade die übersetzte Datei herunter, sobald die Verarbeitung abgeschlossen ist.

![Erweiterte Demo zur Dokumentübersetzung](img/docTranslationAdvancedDemo.png)

## Einrichtung

- **Rollen:** Everybody (konfiguriert in config/roles.xml)
- **OpenAPI:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

Folge diesen Schritten, um deine Axon Ivy Umgebung mit DeepL zu verbinden:

1. Erstelle ein DeepL API-Konto und generiere einen API-Authentifizierungsschlüssel (DeepL API Free oder DeepL API Pro). Der Schlüssel endet üblicherweise auf `:fx`. Details findest du in der [DeepL Authentifizierungsanleitung](https://www.deepl.com/docs-api/api-access/authentication/).
2. Setze die Variable `com.axonivy.connector.deepl.authKey` auf deinen DeepL API-Schlüssel. Der Schlüssel wird wie ein Passwort behandelt und sollte geheim bleiben.
3. Der Connector bringt einen vorkonfigurierten REST-Client (`deepl-connector`) mit, der auf `https://api-free.deepl.com/v2` zeigt. Wenn du ein DeepL API Pro-Konto nutzt, ändere die URL auf den Pro-Endpunkt.
4. Führe einen der Demo-Prozesse aus, um zu bestätigen, dass Übersetzungsanfragen DeepL erreichen und Ergebnisse wie erwartet zurückgegeben werden.

### Variablen

```
@variables.yaml@
```

## Komponenten

### Aufrufbare Subprozesse

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

- Für diese Market-Erweiterung stellen wir keine Dialog-Komponenten bereit.

### Webdienste

Der Connector ruft die DeepL REST API auf. Die von diesem Connector verwendete OpenAPI-Spezifikation ist hier verfügbar:

- **OpenAPI Spec URL:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

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
