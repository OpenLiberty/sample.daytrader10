/**
 * (C) Copyright IBM Corporation 2022.
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
package com.ibm.websphere.samples.daytrader.impl.direct;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.jms.Queue;
import jakarta.jms.QueueConnectionFactory;
import jakarta.jms.Topic;
import jakarta.jms.TopicConnectionFactory;

@ApplicationScoped
public class TradeDirectResources  {
  // For Wildfly - add java:/ to these resource names.

  @Resource(name = "jms/QueueConnectionFactory", authenticationType = jakarta.annotation.Resource.AuthenticationType.APPLICATION)
  private QueueConnectionFactory queueConnectionFactory;

  @Resource(name = "jms/TopicConnectionFactory", authenticationType = jakarta.annotation.Resource.AuthenticationType.APPLICATION)
  private TopicConnectionFactory topicConnectionFactory;

  @Resource(lookup = "jms/TradeStreamerTopic")
  private Topic tradeStreamerTopic;

  @Resource(lookup = "jms/TradeBrokerQueue")
  private Queue tradeBrokerQueue;

  @Resource(lookup = "jdbc/TradeDataSource")
  private DataSource datasource;

  @Resource
  private ManagedExecutorService mes;

  private InitialContext context;

  @PostConstruct
  void init() {
    try {
      context = new InitialContext();
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }

  public QueueConnectionFactory getQueueConnectionFactory() {
    return queueConnectionFactory;
  }

  public TopicConnectionFactory getTopicConnectionFactory() {
    return topicConnectionFactory;
  }

  public Queue getQueue() {
    return tradeBrokerQueue;
  }

  public Topic getTopic() {
    return tradeStreamerTopic;
  }

  public DataSource getDataSource() {
    return datasource;
  }

  public ManagedExecutorService getManagedExecutorService() {
    return mes;
  }

  public InitialContext getContext() {
    return context;
  }
}