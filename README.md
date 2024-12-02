# DeepL Connector

[![CI Build](https://github.com/axonivy-market/deepl-connector/actions/workflows/ci.yml/badge.svg)](https://github.com/axonivy-market/deepl-connector/actions/workflows/ci.yml)

Read our [documentation](deepl-connector-product/README.md).


## Development

To maintain this project and simulate requests there's no necessity to have a DeepL account and accessible environment.

### Mocking
Setup the mock service for DeepL requests, which provides static results. This mock is enabled when running JUnit tests, but it can be enabled for the normal Designer as follows:

- Use your File Manager to navigate into the `configuration` directory of your Axon.ivy Designer.
- Create a file called `app.yaml` and add the following contents with an editor:
    
    ```yaml
    # yaml-language-server: $schema=https://json-schema.axonivy.com/app/12.0.0/app.json
    RestClients:
      deepl:
        Url: '{ivy.app.baseurl}/api/mock'
        Properties:
          AUTH.deepLKey: notMyKey
        Features:
        - ch.ivyteam.ivy.rest.client.mapper.JsonFeature
        - org.glassfish.jersey.media.multipart.MultiPartFeature
        - ch.ivyteam.ivy.rest.client.security.CsrfHeaderFeature
    ```
- Run any startable Process in the Demo project and verify in the RuntimeLog view, that requests are being sent to localhost rathern than deepl.com.

