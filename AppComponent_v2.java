/*
 * Copyright 2017-present Open Networking Foundation
 *
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
 */
package org.upmc.reop.groupe6.app;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.Service;
import org.onlab.packet.Ethernet;
import org.onlab.packet.ICMP;
import org.onlab.packet.MacAddress;
import org.onosproject.core.ApplicationId;
import org.onosproject.core.CoreService;
import org.onosproject.net.Device;
import org.onosproject.net.DeviceId;
import org.onosproject.net.Port;
import org.onosproject.net.PortNumber;
import org.onosproject.net.device.DeviceService;
import org.onosproject.net.device.PortStatistics;
import org.onosproject.net.flow.*;
import org.onosproject.net.flowobjective.FlowObjectiveService;
import org.onosproject.net.intent.PathIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;

/**
 * Skeletal ONOS application component.
 */
@Component(immediate = true)
public class AppComponent {

    private final Logger log = LoggerFactory.getLogger(getClass());


    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected DeviceService deviceService;

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected CoreService coreService;

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected FlowRuleService flowRuleService;


    protected TrafficSelector.Builder selectorBuilder;
    protected TrafficTreatment.Builder treatment;
    protected ApplicationId appId;



    @Activate
    protected void activate() {

        log.info("Started REOP project");

        /*
        ---------- H1 SENDER ----------
         */
        log.info("Conf h1->h2");
        // s1-s4-s2-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:02",
                "of:0000000000000001",
                1,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:02",
                "of:0000000000000002",
                3,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:02",
                "of:0000000000000004",
                3,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:02",
                "of:0000000000000003",
                4,
                3)
                .build());

        log.info("Conf h1->h3");
        // s1-s3-s4-s2

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:03",
                "of:0000000000000001",
                1,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:03",
                "of:0000000000000004",
                5,
                4)
                .build());

        log.info("Conf h1->h4");
        //s1-s4-s3-s2

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:04",
                "of:0000000000000001",
                1,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:04",
                "of:0000000000000004",
                5,
                4)
                .build());

        log.info("Conf h1->h5");
        //s1-s4-s2-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:05",
                "of:0000000000000001",
                1,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:05",
                "of:0000000000000004",
                5,
                3)
                .build());

        log.info("Conf h1->h6");
        //s1-s4-s2-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:06",
                "of:0000000000000001",
                1,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:06",
                "of:0000000000000004",
                5,
                3)
                .build());

        log.info("Conf h1->h7");
        //s1-s3-s2-s4

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:07",
                "of:0000000000000001",
                1,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:07",
                "of:0000000000000003",
                3,
                5)
                .build());

        log.info("Conf h1->h8");
        //s1-s3-s2-s4

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:08",
                "of:0000000000000001",
                1,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:01",
                "00:00:00:00:00:08",
                "of:0000000000000003",
                3,
                5)
                .build());



        /*
        ---------- H2 SENDER ----------
         */
        log.info("Conf h2->h1");
        //s1-s3-s4-s2-s1

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:01",
                "of:0000000000000001",
                2,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:01",
                "of:0000000000000003",
                3,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:01",
                "of:0000000000000004",
                4,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:01",
                "of:0000000000000002",
                4,
                3)
                .build());

        log.info("Conf h2->h3");
        //s1-s4-s3-s2

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:03",
                "of:0000000000000001",
                2,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:03",
                "of:0000000000000003",
                3,
                4)
                .build());

        log.info("Conf h2->h4");
        //s1-s3-s4-s2

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:04",
                "of:0000000000000001",
                2,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:04",
                "of:0000000000000003",
                3,
                4)
                .build());

        log.info("Conf h2->h5");
        //s1-s4-s2-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:05",
                "of:0000000000000001",
                2,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:05",
                "of:0000000000000004",
                5,
                3)
                .build());

        log.info("Conf h2->h6");
        //s1-s4-s2-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:06",
                "of:0000000000000001",
                2,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:06",
                "of:0000000000000004",
                5,
                3)
                .build());

        log.info("Conf h2->h7");
        //s1-s3-s2-s4

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:07",
                "of:0000000000000001",
                2,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:07",
                "of:0000000000000003",
                3,
                5)
                .build());

        log.info("Conf h2->h8");
        //s1-s3-s2-s4

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:08",
                "of:0000000000000001",
                2,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:02",
                "00:00:00:00:00:08",
                "of:0000000000000003",
                3,
                5)
                .build());


         /*
        ---------- H3 SENDER ----------
         */
        log.info("Conf h3->h1");
        //s2-s3-s4-s1

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:01",
                "of:0000000000000002",
                1,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:01",
                "of:0000000000000003",
                5,
                4)
                .build());

        log.info("Conf h3->h2");
        //s2-s4-s3-s1

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:02",
                "of:0000000000000002",
                1,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:02",
                "of:0000000000000004",
                3,
                4)
                .build());

        log.info("Conf h3->h4");
        //s2-s4-s3-s1

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:04",
                "of:0000000000000002",
                1,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:04",
                "of:0000000000000003",
                5,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:04",
                "of:0000000000000004",
                4,
                5)
                .build());

        log.info("Conf h3->h5");
        //s2-s4-s1-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:05",
                "of:0000000000000002",
                1,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:05",
                "of:0000000000000004",
                3,
                5)
                .build());

        log.info("Conf h3->h6");
        //s2-s1-s4-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:06",
                "of:0000000000000002",
                1,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:06",
                "of:0000000000000001",
                3,
                5)
                .build());

        log.info("Conf h3->h7");
        //s2-s1-s3-s4

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:07",
                "of:0000000000000002",
                1,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:07",
                "of:0000000000000001",
                3,
                4)
                .build());

        log.info("Conf h3->h8");
        //

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:08",
                "of:0000000000000002",
                1,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:03",
                "00:00:00:00:00:08",
                "of:0000000000000001",
                5,
                3)
                .build());



        /*
        ---------- H4 SENDER ----------
         */
        log.info("Conf h4->h1");
        //s2-s3-s4-s1

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:01",
                "of:0000000000000002",
                2,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:01",
                "of:0000000000000003",
                5,
                4)
                .build());

        log.info("Conf h4->h2");
        //s2-s4-s3-s1

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:02",
                "of:0000000000000002",
                2,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:02",
                "of:0000000000000004",
                3,
                4)
                .build());

        log.info("Conf h4->h3");
        //s2-s3-s4-s1-s2

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:03",
                "of:0000000000000002",
                2,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:03",
                "of:0000000000000003",
                5,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:03",
                "of:0000000000000004",
                4,
                5)
                .build());

        log.info("Conf h4->h5");
        //s2-s1-s4-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:05",
                "of:0000000000000002",
                2,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:05",
                "of:0000000000000001",
                3,
                5)
                .build());

        log.info("Conf h4->h6");
        //s2-s1-s4-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:06",
                "of:0000000000000002",
                1,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:06",
                "of:0000000000000001",
                3,
                5)
                .build());

        log.info("Conf h4->h7");
        //s2-s3-s1-s4

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:07",
                "of:0000000000000002",
                2,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:07",
                "of:0000000000000003",
                5,
                3)
                .build());

        log.info("Conf h4->h8");
        //s2-s1-s3-s4

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:08",
                "of:0000000000000002",
                2,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:04",
                "00:00:00:00:00:08",
                "of:0000000000000001",
                3,
                4)
                .build());

   /*
        ----- H5 SENDER -----
         */

        log.info("Conf h5->h1");
        // s3-s2-s4-s1

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:01",
                "of:0000000000000003",
                1,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:01",
                "of:0000000000000002",
                5,
                4)
                .build());

        log.info("Conf h5->h2");
        // s3-s2-s4-s1

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:02",
                "of:0000000000000003",
                1,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:02",
                "of:0000000000000002",
                5,
                4)
                .build());

        log.info("Conf h5->h3");
        // s3-s1-s4-s2

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:03",
                "of:0000000000000003",
                1,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:03",
                "of:0000000000000001",
                4,
                5)
                .build());

        log.info("Conf h5->h4");
        // s3-s1-s4-s2

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:04",
                "of:0000000000000003",
                1,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:04",
                "of:0000000000000001",
                4,
                5)
                .build());

        log.info("Conf h5->h6");
        // s3-s2-s4-s1-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:06",
                "of:0000000000000003",
                1,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:06",
                "of:0000000000000002",
                5,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:06",
                "of:0000000000000004",
                3,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:06",
                "of:0000000000000001",
                5,
                4)
                .build());

        log.info("Conf h5->h7");
        // s3-s1-s2-s4

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:07",
                "of:0000000000000003",
                1,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:07",
                "of:0000000000000001",
                4,
                3)
                .build());

        log.info("Conf h5->h8");
        // s3-s1-s2-s4

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:08",
                "of:0000000000000003",
                1,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:05",
                "00:00:00:00:00:08",
                "of:0000000000000001",
                4,
                3)
                .build());

        /*
        ----- H6 SENDER -----
         */

        log.info("Conf h6->h1");
        // s3-s2-s4-s1

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:01",
                "of:0000000000000003",
                2,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:01",
                "of:0000000000000002",
                5,
                4)
                .build());

        log.info("Conf h6->h2");
        // s3-s2-s4-s1

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:02",
                "of:0000000000000003",
                2,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:02",
                "of:0000000000000002",
                5,
                4)
                .build());

        log.info("Conf h6->h3");
        // s3-s1-s4-s2

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:03",
                "of:0000000000000003",
                2,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:03",
                "of:0000000000000001",
                4,
                5)
                .build());

        log.info("Conf h6->h4");
        // s3-s1-s4-s2

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:04",
                "of:0000000000000003",
                2,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:04",
                "of:0000000000000001",
                4,
                5)
                .build());

        log.info("Conf h6->h5");
        // s3-s2-s4-s1-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:05",
                "of:0000000000000003",
                2,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:05",
                "of:0000000000000002",
                5,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:05",
                "of:0000000000000004",
                3,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:05",
                "of:0000000000000001",
                5,
                4)
                .build());

        log.info("Conf h6->h7");
        // s3-s1-s2-s4

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:07",
                "of:0000000000000003",
                2,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:07",
                "of:0000000000000001",
                4,
                3)
                .build());

        log.info("Conf h5->h8");
        // s3-s1-s2-s4

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:08",
                "of:0000000000000003",
                2,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:06",
                "00:00:00:00:00:08",
                "of:0000000000000001",
                4,
                3)
                .build());

        /*
        ----- H7 SENDER -----
         */

        log.info("Conf h7->h1");
        // s4-s2-s3-s1

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:01",
                "of:0000000000000004",
                1,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:01",
                "of:0000000000000002",
                4,
                5)
                .build());

        log.info("Conf h7->h2");
        // s4-s2-s3-s1

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:02",
                "of:0000000000000004",
                1,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:02",
                "of:0000000000000002",
                4,
                5)
                .build());

        log.info("Conf h7->h3");
        // s4-s1-s3-s2

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:03",
                "of:0000000000000004",
                1,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:03",
                "of:0000000000000001",
                5,
                4)
                .build());

        log.info("Conf h7->h4");
        // s4-s1-s3-s2

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:04",
                "of:0000000000000004",
                1,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:04",
                "of:0000000000000001",
                5,
                4)
                .build());

        log.info("Conf h7->h5");
        // s4-s2-s1-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:05",
                "of:0000000000000004",
                1,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:05",
                "of:0000000000000002",
                4,
                3)
                .build());

        log.info("Conf h7->h6");
        // s4-s2-s1-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:06",
                "of:0000000000000004",
                1,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:06",
                "of:0000000000000002",
                4,
                3)
                .build());

        log.info("Conf h7->h8");
        // s4-s2-s1-s3-s4

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:08",
                "of:0000000000000004",
                1,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:08",
                "of:0000000000000002",
                4,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:08",
                "of:0000000000000001",
                3,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:07",
                "00:00:00:00:00:08",
                "of:0000000000000003",
                3,
                4)
                .build());

        /*
        ----- H8 SENDER -----
         */

        log.info("Conf h8->h1");
        // s4-s2-s3-s1

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:01",
                "of:0000000000000004",
                2,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:01",
                "of:0000000000000002",
                4,
                5)
                .build());

        log.info("Conf h8->h2");
        // s4-s2-s3-s1

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:02",
                "of:0000000000000004",
                2,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:02",
                "of:0000000000000002",
                4,
                5)
                .build());

        log.info("Conf h8->h3");
        // s4-s1-s3-s2

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:03",
                "of:0000000000000004",
                2,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:03",
                "of:0000000000000001",
                5,
                4)
                .build());

        log.info("Conf h8->h4");
        // s4-s1-s3-s2

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:04",
                "of:0000000000000004",
                2,
                5)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:04",
                "of:0000000000000001",
                5,
                4)
                .build());

        log.info("Conf h8->h5");
        // s4-s2-s1-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:05",
                "of:0000000000000004",
                2,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:05",
                "of:0000000000000002",
                4,
                3)
                .build());

        log.info("Conf h8->h6");
        // s4-s2-s1-s3

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:06",
                "of:0000000000000004",
                2,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:06",
                "of:0000000000000002",
                4,
                3)
                .build());

        log.info("Conf h8->h7");
        // s4-s2-s1-s3-4

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:07",
                "of:0000000000000004",
                2,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:07",
                "of:0000000000000002",
                4,
                3)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:07",
                "of:0000000000000001",
                3,
                4)
                .build());

        flowRuleService.applyFlowRules(circuitHamiltonienRule(
                "00:00:00:00:00:08",
                "00:00:00:00:00:07",
                "of:0000000000000003",
                3,
                4)
                .build());




    }

    @Deactivate
    protected void deactivate() {
        for(portStatsReaderTask task : this.getMap().values()) {
            task.setExit(true);
            task.getTimer().cancel();
        }
        log.info("Stopped");
    }

    public Map<Integer, portStatsReaderTask> getMap() {
        return map;
    }

    public void setMap(Map<Integer, portStatsReaderTask> map) {
        this.map = map;
    }

    private Map<Integer,portStatsReaderTask> map = new HashMap<Integer,portStatsReaderTask>();

    public FlowRule.Builder circuitHamiltonienRule (String macSrc, String macDst, String switchID,
                                                    long portIn, long portOut) {

        appId = coreService.registerApplication("org.upmc.reop.groupe6.app");

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf(macSrc))
                .matchEthDst(MacAddress.valueOf(macDst))
                .matchInPort(PortNumber.portNumber(portIn));


        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(portOut))
                .immediate();

        FlowRule.Builder builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId(switchID))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        return builder;
    }

}
