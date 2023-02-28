package com.axonivy.connector.deepl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import ch.ivyteam.ivy.IvyConstants;
import ch.ivyteam.ivy.environment.Ivy;

@SuppressWarnings("restriction")
public class FileLoaderUtil {

  public static ch.ivyteam.ivy.scripting.objects.File loadFromWebContent(String relativePath) throws IOException, CoreException {
    IProject project = Ivy.request().getProcessModelVersion().getProject();
    IFile file = project.getFolder(IvyConstants.DIRECTORY_WEB_CONTENT).getFile(relativePath);
    var ivyFile = new ch.ivyteam.ivy.scripting.objects.File(file.getName(), true);
    java.nio.file.Path path = ivyFile.getJavaFile().toPath();
    Files.copy(file.getContents(), path, StandardCopyOption.REPLACE_EXISTING);
    return ivyFile;
  }

}
