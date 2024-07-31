//package bank;
//
//
//import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
//import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
//import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//
//public class LambdaTest {
//    @Test
//    public void testLambdaHandler()throws IOException {
//        Application lambdaHandler = new Application();
//        AwsProxyRequest awsProxyRequest = new AwsProxyRequestBuilder("api/v1/bank/accounts", "GET").build();
//        AwsProxyResponse awsProxyResponse = lambdaHandler.handleRequest(awsProxyRequest, null);
//        Assertions.assertNotNull(awsProxyResponse.getBody());
//        Assertions.assertEquals(200, awsProxyResponse.getStatusCode());
//    }
//}
