package io.tanbit.examples.standalone.mock.args;

import java.util.stream.Stream;

public class RootDirArgs {

  private final String ROOT_DIR_ARG = "--root-dir";
  private final String DEFAULT_ROOT_DIR = "src/main/resources";

  public String[] addRootDirIfNotExist(String[] args) {
    if (!argsContainsRootDir(args)) {
      return addRootDir(args);
    }
    return args;
  }

  private String[] addRootDir(String[] args) {
    String[] argsWithRootDir = new String[args.length + 1];
    System.arraycopy(args, 0, argsWithRootDir, 0, args.length);
    argsWithRootDir[argsWithRootDir.length - 1] = ROOT_DIR_ARG + "=" + DEFAULT_ROOT_DIR;
    return argsWithRootDir;
  }

  private boolean argsContainsRootDir(String[] args) {
    return Stream.of(args).anyMatch(arg -> arg.contains(ROOT_DIR_ARG));
  }

}
