package io.tanbit.examples.standalone.mock.args;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import io.tanbit.examples.standalone.mock.transformers.SessionIdTransformer;

public class ExtensionArgs {

  private final String EXTENSIONS_ARG = "--extensions";
  private static final List<Class<?>> EXTENSION_CLASSES = Arrays.asList(SessionIdTransformer.class);

  public String[] addExtensionsIfExist(String[] args) {
    return getExtensionClasses()
        .map(extensions -> addExtensions(args, extensions))
        .orElse(args);
  }

  private String[] addExtensions(String[] args, String extensions) {
    String[] argsWithExtensions = new String[args.length + 2];
    System.arraycopy(args, 0, argsWithExtensions, 0, args.length);
    argsWithExtensions[argsWithExtensions.length - 2] = EXTENSIONS_ARG;
    argsWithExtensions[argsWithExtensions.length - 1] = extensions;
    return argsWithExtensions;
  }

  private Optional<String> getExtensionClasses() {
    return EXTENSION_CLASSES.stream()
        .map(Class::getName)
        .reduce((val1, val2) -> val1 + "," + val2);
  }


}
