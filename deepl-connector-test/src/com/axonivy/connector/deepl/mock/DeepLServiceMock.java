package com.axonivy.connector.deepl.mock;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import ch.ivyteam.ivy.rest.client.config.IvyDefaultJaxRsTemplates;
import io.swagger.v3.oas.annotations.Hidden;

/**
 * A simple REST backend for a third-party service used in tests:<br/>
 * - provides static results and therefore simplifies our tests <br/>
 * - does not require authentication or real-world secrets <br/>
 * - built with ivy standard tools: easy to maintain for everyone <br/>
 */
@Path(DeepLServiceMock.PATH_SUFFIX)
@PermitAll // allow unauthenticated calls
@Hidden // do not show me on swagger-ui or openapi3 resources.
@SuppressWarnings("all")
public class DeepLServiceMock {

  static final String PATH_SUFFIX = "mock";

  // URI where this mock can be reached: to be referenced in tests that use it!
  public static final String URI = "{"+IvyDefaultJaxRsTemplates.APP_URL+"}/api/"+PATH_SUFFIX;

  @POST
  @Path("translate")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response findCustomer(
//    @FormDataParam("text") String text,
//    @FormDataParam("target_lang") String target
    ) {
    return Response.ok()
      .entity(load("translate.json"))
      .build();
  }

  private static String load(String json) {
    try (var is = DeepLServiceMock.class.getResourceAsStream("json/"+json)) {
      if (is == null) {
        throw new RuntimeException("The json file '"+json+"' does not exist.");
      }
      return IOUtils.toString(is, StandardCharsets.UTF_8);
    } catch (IOException ex) {
      throw new RuntimeException("Failed to read json "+json, ex);
    }
  }
}
