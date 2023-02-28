package com.axonivy.connector.deepl.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.connector.deepl.mock.DeepLServiceMock;
import com.deepl.api.v2.client.TargetLanguage;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.rest.client.RestClient;
import ch.ivyteam.ivy.rest.client.RestClients;
import ch.ivyteam.ivy.rest.client.security.CsrfHeaderFeature;
import ch.ivyteam.ivy.scripting.objects.File;

@IvyProcessTest
public class DeepLTest{

  private static final BpmProcess DEEPL = BpmProcess.path("deepl/translate");

  private interface Start {
    BpmElement TRANSLATE = DEEPL.elementName("text(String,String)");
    BpmElement DOCUMENT = DEEPL.elementName("DocumentSample(File,String)");
  }

  @BeforeEach
  void setup(AppFixture fixture, IApplication app) {
    fixture.config("RestClients.deepl.Url", DeepLServiceMock.URI);
    fixture.var("deepl-connector.apiKey", "notMyKey:fx");
    RestClients clients = RestClients.of(app);
    RestClient deepL = clients.find("deepl");
    var testClient = deepL.toBuilder()
      .feature(CsrfHeaderFeature.class.getName())
      .property("AUTH.deepLKey", "notMyKey:fx")
      .toRestClient();
    clients.set(testClient);
  }

  @Test
  public void translate(BpmClient bpmClient){
    ExecutionResult result = bpmClient.start()
      .subProcess(Start.TRANSLATE)
      .withParam("text", "Hello World")
      .withParam("targetLanguage", TargetLanguage.DE.getValue())
      .execute();
    deepl.translate.Data data = result.data().last();
    assertThat(data.getOutput()).isEqualTo("Hallo Welt");
  }

  @Test
  public void document(BpmClient bpmClient) throws IOException{
    File original = new File("juhu.pdf");
    original.write("hoi pdf");
    ExecutionResult result = bpmClient.start()
      .subProcess(Start.DOCUMENT)
      .withParam("file", original)
      .withParam("targetLanguage", TargetLanguage.DE.getValue())
      .execute();
    deepl.translate.Data data = result.data().last();
    File translated = data.getTranslated();
    assertThat(translated).isNotNull();
    assertThat(translated.getJavaFile()).exists();
  }

}
