package com.axonivy.connector.deepl;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;

public class FileLoaderUtil {

  public static ch.ivyteam.ivy.scripting.objects.File loadFromWebContent(String relativePath) throws IOException, CoreException {
    // IProcessModelVersion.current().project().
    // IProject project = IIvyProject.current().getProject();
    // IFile file = project.getFolder(IvyConstants.DIRECTORY_WEB_CONTENT).getFile(relativePath);
    var ivyFile = new ch.ivyteam.ivy.scripting.objects.File("to-be-fixed", true);
    // java.nio.file.Path path = ivyFile.getJavaFile().toPath();
    // Files.copy(file.getContents(), path, StandardCopyOption.REPLACE_EXISTING);
    return ivyFile;
  }

}
