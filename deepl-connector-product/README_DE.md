Mit dem Deepl-Übersetzungsservice von Axon Ivy kannst du Sprachbarrieren in der Prozessautomatisierung überwinden. Der Konnektor lässt sich nahtlos in deine bestehenden Workflows integrieren und ermöglicht Dir mehrsprachige Kommunikation. Der Deepl-Konnektor:

- basiert auf der OpenAPI-Spezifikation.
- übersetzt ganze Word- (.docx), PowerPoint- (.pptx), PDF- (.pdf), Text- (.txt) und HTML-Dateien (.html).
- gibt dir die volle Leistungsfähigkeit aller DeepL-API-Funktionen.
- unterstützt dich mit einer Demo-Implementierung, um deinen Integrationsaufwand zu reduzieren.
- ermöglicht es Low-Code Citizen Developern, mehrsprachige Benutzeroberflächen bereitzustellen.

## Demo

The document translation demo shows how easy it has become, to translate documents throughout your workflow, into the language of your end user.

![deepl-doc-demo](img/docTranslationDemo.png)

Of course pure text translations are demonstrated too.
This is very handy to translate user input which was written in a foreign language.

![deepl-txt-demo](img/txtTranslateDemo.png)

To use DeepL powered translations, we have created a minimal simplistic interface without technical difficulties.
Get inspired and use DeepL anywhere in your workflow.

![deepl-activity](img/deeplSubCallActivity.png)

## Setup

Get a [free developer account](https://www.deepl.com/pro#developer) from DeepL.com
Copy the API-Key of your account into

`config/variables.yaml` under
`variables.deepl-connector.authKey`

```
@variables.yaml@
```

