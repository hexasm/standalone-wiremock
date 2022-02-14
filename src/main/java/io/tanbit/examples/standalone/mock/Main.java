package io.tanbit.examples.standalone.mock;

import java.util.stream.Stream;

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;
import io.tanbit.examples.standalone.mock.args.ExtensionArgs;
import io.tanbit.examples.standalone.mock.args.RootDirArgs;

public class Main {

  public static void main(String[] args) {
    args = new ExtensionArgs().addExtensionsIfExist(args);
    args = new RootDirArgs().addRootDirIfNotExist(args);
    printArgs(args);
    new WireMockServerRunner().run(args);
  }

  private static void printArgs(String[] args) {
    System.out.println("args");
    Stream.of(args).forEach(System.out::println);
  }
}
