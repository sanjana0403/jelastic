/*
 * Copyright 2019 Koushik R <rkoushik.14@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.jelastic.core.health;

import com.codahale.metrics.health.HealthCheck;
import io.github.jelastic.core.config.JElasticConfiguration;
import io.github.jelastic.core.elastic.ElasticClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.cluster.health.ClusterHealthStatus;


/**
 * Created by koushikr
 */
@Slf4j
public class EsClientHealth extends HealthCheck {

    private final ElasticClient elasticClient;
    private final JElasticConfiguration JElasticConfiguration;

    public EsClientHealth(ElasticClient elasticClient, JElasticConfiguration JElasticConfiguration) {
        this.elasticClient = elasticClient;
        this.JElasticConfiguration = JElasticConfiguration;
    }

    @Override
    protected Result check() {
        final ClusterHealthStatus status = elasticClient.getClient().admin().cluster().prepareHealth()
                .get().getStatus();

        if (status == ClusterHealthStatus.RED || (JElasticConfiguration.isFailOnYellow()
                && status == ClusterHealthStatus.YELLOW)) {
            return Result.unhealthy("Last status: %s", status.name());
        } else {
            return Result.healthy("Last status: %s", status.name());
        }

    }
}