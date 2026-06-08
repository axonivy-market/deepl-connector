# DeepL Connector

Der DeepL Connector integriert die leistungsstarke [DeepL-Übersetzungs-API](https://www.deepl.com/docs-api/) in Axon Ivy und ermöglicht dir nahtlose, hochwertige maschinelle Übersetzungen direkt in deinen Geschäftsprozessen. Egal ob du einen kurzen Text oder ein vollständiges Dokument übersetzen möchtest — der Connector übernimmt das für dich, gestützt auf eine der weltweit genauesten Übersetzungsmaschinen.

![DeepL Callable Sub Activity](img/deeplSubCallActivity.png)

**Wichtigste Funktionen**

- Übersetze Texte aus jeder Quellsprache in jede von DeepL unterstützte Zielsprache direkt aus deinen Prozessen heraus
- Übersetze vollständige Dokumente (DOCX, PDF, PPTX, HTML, TXT) und erhalte die übersetzte Datei als Ausgabe
- Erkenne die Quellsprache automatisch oder gib sie explizit an, um die volle Kontrolle über die Übersetzung zu behalten
- Passe das Übersetzungsverhalten mit Formalitätsgrad, Tag-Verarbeitung, Satztrennung und Glossar-Einstellungen an
- Authentifiziere dich sicher über einen konfigurierbaren DeepL-API-Schlüssel, der als Axon-Ivy-Variable gespeichert ist
- Erkunde fertige Demo-Workflows für Textübersetzung und Dokumentübersetzung

## Demo

Probiere die enthaltenen Demo-Workflows aus, um den DeepL Connector in Aktion zu erleben. Die Demos zeigen, wie du einen Textausschnitt übersetzt und wie du ein vollständiges Dokument hochlädst und mit der DeepL-API übersetzt.

### Demo-Workflows

##### Text übersetzen

1. Starte den Demo-Workflow „Text übersetzen" aus dem Demo-Menü.
2. Du siehst ein Formular mit einem Texteingabefeld, einem Quellsprachen-Selektor und einem Zielsprachen-Selektor.

![Translate Text Demo](img/txtTranslateDemo.png)

3. Gib deinen Text ein oder ändere den Beispieltext im Eingabefeld (Standard ist `<h1>Hello world</h1>`).
4. Wähle die gewünschte Zielsprache und optional eine Quellsprache — wenn du das Feld leer lässt, wird die Sprache automatisch erkannt.
5. Klicke auf Übersetzen — der übersetzte Text erscheint sofort im Ausgabebereich darunter.

##### Datei übersetzen

1. Starte den Demo-Workflow „Datei übersetzen" aus dem Demo-Menü.
2. Du siehst ein Upload-Formular, in dem du eine Dokumentdatei (DOCX, PDF, PPTX, TXT oder HTML) auswählen kannst.
3. Wähle Quell- und Zielsprache, lade dann deine Datei hoch oder verwende die mitgelieferte Beispielrechnung.

![Translate File Demo](img/docTranslationDemo.png)

4. Klicke auf Hochladen — der Connector sendet dein Dokument an DeepL und wartet, bis die Übersetzung abgeschlossen ist.
5. Sobald die Übersetzung fertig ist, erscheint ein Download-Link, über den du die übersetzte Datei direkt herunterladen kannst.

## Setup

- **Rollen:** Everybody (konfiguriert in config/roles.xml)
- **OpenAPI:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

1. Registriere dich für ein [DeepL-API-Konto](https://www.deepl.com/pro-api) und hole dir deinen Authentifizierungsschlüssel (Schlüssel für das kostenlose Kontingent enden mit `:fx`).
2. Setze in deinem Axon-Ivy-Projekt die Variable `com.axonivy.connector.deepl.authKey` auf deinen DeepL-API-Schlüssel.
3. Der Connector verwendet standardmäßig den kostenlosen DeepL-API-Endpunkt (`https://api-free.deepl.com/v2`). Für kostenpflichtige Konten aktualisiere die `Url` in der REST-Client-Konfiguration `deepl-connector` auf `https://api.deepl.com/v2`.
4. Überprüfe das Setup, indem du einen der Demo-Workflows ausführst und eine erfolgreiche Übersetzungsantwort erhältst.

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

- **Signatur**: document(File file, com.deepl.api.v2.client.TargetLanguage targetLanguage) -> translated: File
    - Eingabe:
        - `file` (File) - Eine zu übersetzende Datei (z. B. docx, pdf, pptx)
        - `targetLanguage` (com.deepl.api.v2.client.TargetLanguage) - Die Sprache, in die übersetzt werden soll
    - Ergebnis:
        - `translated` (File)

### Dialogkomponenten

#### translateFile — Dokument hochladen und übersetzen

- **Namespace:** com.axonivy.connector.deepl.demo.translateFile
- **Komponententyp:** UI-Dialog
- **Felder:** - (keine)
- **Zweck:** Ermöglicht es Benutzern, ein Dokument hochzuladen, Quell- und Zielsprache auszuwählen und die übersetzte Ergebnisdatei herunterzuladen.

#### translateText — Text übersetzen

- **Namespace:** com.axonivy.connector.deepl.demo.translateText
- **Komponententyp:** Formular-Dialog
- **Felder:** - (keine)
- **Zweck:** Stellt ein einfaches Formular zur Verfügung, um Text einzugeben, Quell- und Zielsprache zu wählen und das Übersetzungsergebnis direkt anzuzeigen.

### Webservices

- **OpenAPI-Spec-URL:** https://raw.githubusercontent.com/DeepLcom/openapi/main/openapi.yaml

### Maven-Artefakte

1. deepl-connector

```xml
<dependency>
  <groupId>com.axonivy.connector.deepl</groupId>
  <artifactId>deepl-connector</artifactId>
  <version>@version@</version>
  <type>iar</type>
</dependency>
```

2. deepl-connector-demo

```xml
<dependency>
  <groupId>com.axonivy.connector.deepl</groupId>
  <artifactId>deepl-connector-demo</artifactId>
  <version>@version@</version>
  <type>iar</type>
</dependency>
```
