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

package com.netflix.spinnaker.clouddriver.lambda.provider.view;

import com.amazonaws.services.lambda.model.FunctionConfiguration;
import com.netflix.spinnaker.clouddriver.lambda.model.AwsLambdaFunctionConfiguration;
import com.netflix.spinnaker.clouddriver.lambda.security.NetflixAwsLambdaCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class AwsLambdaPrimitiveConverter {

  private final AwsLambdaAccountMapper accountMapper;

  @Autowired
  public AwsLambdaPrimitiveConverter(AwsLambdaAccountMapper accountMapper) {
    this.accountMapper = accountMapper;
  }

}
