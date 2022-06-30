package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.assertions.Template;
import java.io.IOException;

import java.util.Map;
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

    template.hasResourceProperties("AWS::Lambda::Function", Map.of(
      "FunctionName", "password-generator-function",
      "MemorySize", 1536,
      "Runtime", "java11",
      "Timeout", 45,
      "Handler", "generator.Generate"
      )
    );

    template.hasResourceProperties("AWS::Lambda::Function", Map.of(
      "FunctionName", "password-retrieval-function",
      "MemorySize", 1536,
      "Runtime", "java11",
      "Timeout", 45,
      "Handler", "retriever.Retrieve"
      )
    );

    template.hasResourceProperties("AWS::Lambda::Permission", Map.of(
      "Principal", "apigateway.amazonaws.com",
      "Action", "lambda:InvokeFunction"
      )
    );

    template.hasResourceProperties("AWS::IAM::Policy", Map.of(
      "PolicyDocument", Map.of(
        "Statement", List.of(
          Map.of(
            "Action", List.of(
              "s3:PutObject",
              "s3:GetObject"
            ),
            "Effect", "Allow"
          )
        ),
        "Version", "2012-10-17"
      ),
      "PolicyName", "passwordGeneratorFunctionServiceRoleDefaultPolicy6B11B67C"
      )
    );
  }
}
