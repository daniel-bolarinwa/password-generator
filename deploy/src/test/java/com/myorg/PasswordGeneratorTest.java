package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.assertions.Template;
import software.amazon.awscdk.assertions.Match;
import java.io.IOException;

import java.util.Map;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;


public class PasswordGeneratorTest {

  @Test
  public void testStack() throws IOException {
    App app = new App();
    PasswordGeneratorStack stack = new PasswordGeneratorStack(app, "test");

    Template template = Template.fromStack(stack);

    template.hasResourceProperties("AWS::S3::Bucket", Map.of(
      "BucketName", "password-file-bucket",
      "VersioningConfiguration", Map.of(
        "Status", "Enabled"
      ),
      "BucketEncryption", Map.of(
        "ServerSideEncryptionConfiguration", List.of(
          Map.of(
            "ServerSideEncryptionByDefault", Map.of(
                "SSEAlgorithm", "AES256"
              )
            )
          )
        )
      )
    );
  }
}
