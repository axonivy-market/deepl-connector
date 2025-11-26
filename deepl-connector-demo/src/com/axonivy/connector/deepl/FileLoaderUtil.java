package com.axonivy.connector.deepl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.File;

public class FileLoaderUtil {

  public static File loadFileFromCms(String fileName, String cmsPath) throws IOException {
    var ivyFile = new File(fileName, true);
    Path path = ivyFile.getJavaFile().toPath();
    Files.copy(Ivy.cms().get(cmsPath).get().value().get().read().inputStream(), path,
        StandardCopyOption.REPLACE_EXISTING);
    return ivyFile;
  }
}
