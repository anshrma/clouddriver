/*
 * Copyright 2018 Amazon.com, Inc. or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.clouddriver.lambda.deploy.ops;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.model.DeleteFunctionRequest;
import com.amazonaws.services.lambda.model.DeleteFunctionResult;
import com.netflix.spinnaker.clouddriver.lambda.cache.model.AwsLambdaCacheModel;
import com.netflix.spinnaker.clouddriver.lambda.deploy.description.DeleteLambdaDescription;
import com.netflix.spinnaker.clouddriver.orchestration.AtomicOperation;

import java.util.List;

public class DeleteLambdaAtomicOperation extends AbstractAwsLambdaAtomicOperation<DeleteLambdaDescription, DeleteFunctionResult> implements AtomicOperation<DeleteFunctionResult> {


  public DeleteLambdaAtomicOperation(DeleteLambdaDescription description) {
    super(description, "DELETE_LAMBDA_FUNCTION_CODE");
  }

  @Override
  public DeleteFunctionResult operate(List priorOutputs) {
    updateTaskStatus("Initializing deletion of AWS Lambda Function Operation...");
    return deleteFunctionResult();
  }

  private DeleteFunctionResult deleteFunctionResult (){
    String application = description.getProperty("application").toString();
    String region = description.getProperty("region").toString();
    String account = description.getAccount();
    AwsLambdaCacheModel cache = awsLambdaProvider.getAwsLambdaFunction(application, region, account);


    AWSLambda client = getAwsLambdaClient();
    DeleteFunctionRequest request = new DeleteFunctionRequest()
      .withFunctionName(cache.getFunctionArn());

    DeleteFunctionResult result = client.deleteFunction(request);
    updateTaskStatus("Finished deletion of AWS Lambda Function  Operation...");

    return result;
  }

}
