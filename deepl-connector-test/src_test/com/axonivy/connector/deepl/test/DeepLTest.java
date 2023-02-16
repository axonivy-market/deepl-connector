package com.axonivy.connector.deepl.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.connector.deepl.mock.DeepLServiceMock;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.rest.client.security.CsrfHeaderFeature;

@IvyProcessTest
public class DeepLTest{

  private static final BpmProcess DEEPL = BpmProcess.path("deepl/translate");

  private interface Start {
    BpmElement TRANSLATE = DEEPL.elementName("text(String,TargetLanguage)");
  }

  @BeforeEach
  void setup(AppFixture fixture) {
    fixture.config("RestClients.deepl.Url", DeepLServiceMock.URI);
    fixture.config("RestClients.deepl.Features", CsrfHeaderFeature.class.getName());
    fixture.var("deepl-connector.apiKey", "notMyKey:fx");
  }

  @Test
  public void translate(BpmClient bpmClient){
    ExecutionResult result = bpmClient.start()
      .subProcess(Start.TRANSLATE)
      .withParam("text", "Hello World")
      //.withParam("targetLang", TargetLanguage.DE)
      .execute();
    deepl.translate.Data data = result.data().last();
    assertThat(data.getOutput()).isEqualTo("Hallo Welt");
  }

}
