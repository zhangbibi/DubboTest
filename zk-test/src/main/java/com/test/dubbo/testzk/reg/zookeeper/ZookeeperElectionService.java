/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.test.dubbo.testzk.reg.zookeeper;

import com.test.dubbo.testzk.reg.base.ElectionCandidate;
import java.util.concurrent.CountDownLatch;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

/**
 * 使用{@link LeaderSelector}实现选举服务.
 * 
 * @author gaohongtao
 * @author caohao
 */
public final class ZookeeperElectionService {
    
    private final CountDownLatch leaderLatch = new CountDownLatch(1);
    
    private final LeaderSelector leaderSelector;
    
    public ZookeeperElectionService(final String identity, final CuratorFramework client, final String electionPath, final ElectionCandidate electionCandidate) {
        leaderSelector = new LeaderSelector(client, electionPath, new LeaderSelectorListenerAdapter() {
            
            @Override
            public void takeLeadership(final CuratorFramework client) throws Exception {
                System.out.println("Elastic job: {} has leadership"+ identity);
                try {
                    electionCandidate.startLeadership();
                    leaderLatch.await();
                    System.out.println("Elastic job: {} lost leadership."+ identity);
                    electionCandidate.stopLeadership();
                } catch (final Exception exception) {
                    System.out.println("Elastic job: Starting error"+ exception);
                    System.exit(1);  
                }
            }
        });
        leaderSelector.autoRequeue();
        leaderSelector.setId(identity);
    }
    
    /**
     * 开始选举.
     */
    public void start() {
        System.out.println("Elastic job: {} start to elect leadership"+ leaderSelector.getId());
        leaderSelector.start();
    }
    
    /**
     * 停止选举.
     */
    public void stop() {
        System.out.println("Elastic job: stop leadership election");
        leaderLatch.countDown();
        try {
            leaderSelector.close();
            // CHECKSTYLE:OFF
        } catch (final Exception ignored) {
        }
        // CHECKSTYLE:ON
    }
}
