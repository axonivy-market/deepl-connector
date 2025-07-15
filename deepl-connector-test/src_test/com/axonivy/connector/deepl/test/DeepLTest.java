package com.axonivy.connector.deepl.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import com.deepl.api.v2.client.SourceLanguage;
import com.deepl.api.v2.client.TargetLanguage;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.api.model.PortBinding;

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
import deepl.translate.Options;

@IvyProcessTest
public class DeepLTest{

  private static final BpmProcess DEEPL = BpmProcess.path("deepl/translate");
  private static final String DEFAULT_TEST_INSTANCE_URL = "http://localhost:3000/v2";
  private static final String DEFAULT_TEST_INSTANCE_KEY = "octopus-test";
  private static final String TEST_TEXT_IN_ENGLISH = "proton beam";
  private static final String RESULT_TEXT_IN_GERMAN = "Protonenstrahl";
  private static final String TEST_FILE_NAME = "test.txt";
  private static final DockerImageName DEEPL_TEST_IMAGE = DockerImageName.parse("ghcr.io/axonivy-market/mock-deepl-test-image:latest");
  private static final String FINISHED_SET_UP_LOG_REGEX = ".*mock-proxy-server listening on port 3001.*";
  private static GenericContainer<?> deeplContainer;

  private interface Start {
    BpmElement TRANSLATE = DEEPL.elementName("text(String,TargetLanguage)");
    BpmElement DOCUMENT = DEEPL.elementName("document(File,TargetLanguage)");
    BpmElement TRANSLATE_ADVANCED = DEEPL.elementName("text(String,Options)");
    BpmElement DOCUMENT_ADVANCED = DEEPL.elementName("document(File,Options)");
  }
  
	@SuppressWarnings("resource")
	@BeforeAll
	static void setupConfig() throws InterruptedException {
		Network network = Network.newNetwork();
		deeplContainer = new GenericContainer<>(DEEPL_TEST_IMAGE).withNetwork(network)
				.withNetworkAliases("octopus_deepl").withExposedPorts(3000, 3001)
				.withCreateContainerCmdModifier(
						cmd -> cmd.withHostConfig(HostConfig.newHostConfig().withNetworkMode(network.getId())
								.withPortBindings(buildPortBinding(3000), buildPortBinding(3001))))
				.waitingFor(Wait.forLogMessage(FINISHED_SET_UP_LOG_REGEX, 1));
		deeplContainer.start();
	}
	
	private static PortBinding buildPortBinding(int port) {
		return new PortBinding(Ports.Binding.bindPort(port), new ExposedPort(port));
	}

	@AfterAll
	static void clearTestContainer() {
		deeplContainer.close();
	}

  @BeforeEach
  void setup(AppFixture fixture, IApplication app) {
    fixture.config("RestClients.deepl-connector.Url", DEFAULT_TEST_INSTANCE_URL);
    fixture.var("deepl-connector.apiKey", DEFAULT_TEST_INSTANCE_KEY);
    RestClients clients = RestClients.of(app);
    RestClient deepL = clients.find("deepl-connector");
    var testClient = deepL.toBuilder()
      .feature(CsrfHeaderFeature.class.getName())
      .property("AUTH.deepLKey", DEFAULT_TEST_INSTANCE_KEY)
      .toRestClient();
    clients.set(testClient);
  }

  @Test
  public void translate(BpmClient bpmClient){
    ExecutionResult result = bpmClient.start()
      .subProcess(Start.TRANSLATE)
      .withParam("text", TEST_TEXT_IN_ENGLISH)
      .withParam("targetLanguage", TargetLanguage.DE)
      .execute();
    deepl.translate.Data data = result.data().last();
    assertThat(data.getOutput()).isEqualTo(RESULT_TEXT_IN_GERMAN);
  }

  @Test
  public void document(BpmClient bpmClient) throws IOException{
    File original = new File(TEST_FILE_NAME);
    original.write(TEST_TEXT_IN_ENGLISH);
    ExecutionResult result = bpmClient.start()
      .subProcess(Start.DOCUMENT)
      .withParam("file", original)
      .withParam("targetLanguage", TargetLanguage.DE)
      .execute();
    deepl.translate.Data data = result.data().last();
    File translated = data.getTranslated();
    assertThat(translated).isNotNull();
    assertThat(translated.getJavaFile()).exists();
  }
  
  @Test
  public void translateAdvanced(BpmClient bpmClient){
    ExecutionResult result = bpmClient.start()
      .subProcess(Start.TRANSLATE_ADVANCED)
      .withParam("text", TEST_TEXT_IN_ENGLISH)
      .withParam("options", getDefaultOptions())
      .execute();
    deepl.translate.Data data = result.data().last();
    assertThat(data.getOutput()).isEqualTo(RESULT_TEXT_IN_GERMAN);
  }

  @Test
  public void documentAdvanced(BpmClient bpmClient) throws IOException{
    File original = new File(TEST_FILE_NAME);
    original.write(TEST_TEXT_IN_ENGLISH);
    ExecutionResult result = bpmClient.start()
      .subProcess(Start.DOCUMENT_ADVANCED)
      .withParam("file", original)
      .withParam("options", getDefaultOptions())
      .execute();
    deepl.translate.Data data = result.data().last();
    File translated = data.getTranslated();
    assertThat(translated).isNotNull();
    assertThat(translated.getJavaFile()).exists();
  }
  
  private Options getDefaultOptions() {
	  Options opt = new Options();
	  opt.setSourceLang(SourceLanguage.EN);
	  opt.setTargetLang(TargetLanguage.DE);
	  opt.setTagHandling("html");
	  return opt;
  }

}
