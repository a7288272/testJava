package cn.xuanma.test.controller;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author:wangshu'an
 * @date:2022/11/15 18:05
 * @Description:
 */
@RestController
@Slf4j
public class sendMessage {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage(){
        String messageId= String.valueOf(UUID.randomUUID());
        String messgageName="hello world!";
        String creatTime= DateUtil.now();
        Map<String,Object> map = new HashMap<>();
        map.put("messageId",messageId);
        map.put("messgageName",messgageName);
        map.put("creatTime",creatTime);
        rabbitTemplate.convertAndSend("testDirectExchange","testDirectRouting",map);
        return "send sendDirectMessage ok";
    }

    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage(){
        String messageId= String.valueOf(UUID.randomUUID());
        String messgageName="woman say: hello world!";
        String creatTime= DateUtil.now();
        Map<String,Object> map = new HashMap<>();
        map.put("messageId",messageId);
        map.put("messgageName",messgageName);
        map.put("creatTime",creatTime);
        System.out.println("sendFanoutMessage");
        rabbitTemplate.convertAndSend("fanoutExchange",null,map);
        return "send sendFanoutMessage ok";
    }


    @GetMapping("/sendTopicMessageMan")
    public String sendTopicMessageMan(){
        String messageId= String.valueOf(UUID.randomUUID());
        String messgageName=" man say ：hello world!";
        String creatTime= DateUtil.now();
        Map<String,Object> map = new HashMap<>();
        map.put("messageId",messageId);
        map.put("messgageName",messgageName);
        map.put("creatTime",creatTime);
        System.out.println("sendTopicMessageMan");
        rabbitTemplate.convertAndSend("topicExchange","topic.man",map);
        return "send sendTopicMessageMan ok";
    }
    @GetMapping("/sendTopicMessageWoman")
    public String sendTopicMessageWoman(){
        String messageId= String.valueOf(UUID.randomUUID());
        String messgageName="woman say: hello world!";
        String creatTime= DateUtil.now();
        Map<String,Object> map = new HashMap<>();
        map.put("messageId",messageId);
        map.put("messgageName",messgageName);
        map.put("creatTime",creatTime);
        System.out.println("sendTopicMessageMan");
        rabbitTemplate.convertAndSend("topicExchange","topic.woman",map);
        return "send sendTopicMessageWoman ok";
    }

}
