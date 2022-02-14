package io.tanbit.examples.standalone.mock.transformers;

import java.nio.charset.StandardCharsets;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.BinaryFile;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

public class SessionIdTransformer extends ResponseDefinitionTransformer {

    private static final String TRANSFORMER_NAME = "session-id-transformer";
    private static final boolean APPLY_GLOBALLY = false;


    @Override
    public String getName() {
        return TRANSFORMER_NAME;
    }

    @Override
    public boolean applyGlobally() {
        return APPLY_GLOBALLY;
    }

    @Override
    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource fileSource, Parameters parameters) {
        if (hasEmptyResponseBody(responseDefinition)) {
            return responseDefinition;
        }
        String responseBody = getResponseBody(responseDefinition, fileSource);

        String url = request.getUrl();
        int index= url.lastIndexOf('/');
        String sessionId = url.substring(index +1);
        return ResponseDefinitionBuilder
            .like(responseDefinition).but()
            .withBodyFile(null)
            .withBody(transformBody(responseBody, sessionId))
            .build();
    }

    private String transformBody(String placeholder, String sessionId) {
        return placeholder.replace("sessionId", sessionId);
    }

    private boolean hasEmptyResponseBody(ResponseDefinition responseDefinition) {
        return responseDefinition.getBody() == null && responseDefinition.getBodyFileName() == null;
    }

    private String getResponseBody(ResponseDefinition responseDefinition, FileSource fileSource) {
        String body;
        if (responseDefinition.getBody() != null) {
            body = responseDefinition.getBody();
        } else {
            BinaryFile binaryFile = fileSource.getBinaryFileNamed(responseDefinition.getBodyFileName());
            body = new String(binaryFile.readContents(), StandardCharsets.UTF_8);
        }
        return body;
    }

}
