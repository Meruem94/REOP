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

        log.info("Started");

        appId = coreService.registerApplication("org.upmc.reop.groupe6.app");

        log.info("Conf h1->h2");

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:02"))
                .matchInPort(PortNumber.portNumber(1));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(5))
                .immediate();

        FlowRule.Builder builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000001"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:02"))
                .matchInPort(PortNumber.portNumber(5));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(3))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000004"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:02"))
                .matchInPort(PortNumber.portNumber(4));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(5))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000002"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:02"))
                .matchInPort(PortNumber.portNumber(5));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(3))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000003"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());


        log.info("Conf h1->h3");

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:03"))
                .matchInPort(PortNumber.portNumber(1));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(4))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000001"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:03"))
                .matchInPort(PortNumber.portNumber(3));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(4))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000003"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:03"))
                .matchInPort(PortNumber.portNumber(4));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(3))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000004"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:03"))
                .matchInPort(PortNumber.portNumber(4));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(1))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000002"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        log.info("Conf h1->h4");

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:04"))
                .matchInPort(PortNumber.portNumber(1));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(4))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000001"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:04"))
                .matchInPort(PortNumber.portNumber(3));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(4))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000003"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:04"))
                .matchInPort(PortNumber.portNumber(4));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(3))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000004"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:04"))
                .matchInPort(PortNumber.portNumber(4));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(2))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000002"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        log.info("Conf h1->h5");

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:05"))
                .matchInPort(PortNumber.portNumber(1));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(5))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000001"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:05"))
                .matchInPort(PortNumber.portNumber(5));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(3))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000004"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:05"))
                .matchInPort(PortNumber.portNumber(4));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(5))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000002"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:05"))
                .matchInPort(PortNumber.portNumber(5));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(1))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000003"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());






























        log.info("Conf h1->h8");

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:08"))
                .matchInPort(PortNumber.portNumber(1));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(4))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000001"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:08"))
                .matchInPort(PortNumber.portNumber(3));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(5))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000003"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());


























































        log.info("Conf h8->h1");

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:08"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchInPort(PortNumber.portNumber(2));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(3))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000004"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());

        selectorBuilder = DefaultTrafficSelector.builder()
                .matchEthType(Ethernet.TYPE_IPV4)
                .matchEthSrc(MacAddress.valueOf("00:00:00:00:00:08"))
                .matchEthDst(MacAddress.valueOf("00:00:00:00:00:01"))
                .matchInPort(PortNumber.portNumber(4));

        treatment = DefaultTrafficTreatment.builder()
                .setOutput(PortNumber.portNumber(5))
                .immediate();

        builder = DefaultFlowRule.builder()
                .fromApp(appId)
                .forDevice(DeviceId.deviceId("of:0000000000000002"))
                .withPriority(500)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatment.build())
                .makePermanent();

        flowRuleService.applyFlowRules(builder.build());



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

}
