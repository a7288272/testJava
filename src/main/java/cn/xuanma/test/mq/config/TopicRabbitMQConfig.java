package cn.xuanma.test.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:wangshu'an
 * @date:2022/11/16 9:10
 * @Description:
 */
@Configuration
public class TopicRabbitMQConfig {
    private  final static String man="topic.man";
    private final static String woman="topic.woman";


    /**
     * 第一个队列man
     *
     * @return
     */
    @Bean
    public Queue firstQueue() {
        return new Queue(TopicRabbitMQConfig.man,true);
    }

    /**
     * 第二个队列 woman
     *
     * @return
     */

    @Bean
    public Queue secondQueue() {
        return new Queue(TopicRabbitMQConfig.woman,true);
    }
    /**
     * 主体交换机
     *
     * @return
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 将队列man绑定到交换机中 绑定的键值为topic.man
     * 这样只有消息是携带路由键是topic.man的才会分发到改队列
     *
     * @return
     */
    @Bean
    public Binding bindingMan() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with("topic.man");
    }

    /**
     * 将队列woman绑定到交换机中 绑定的键值为topic.woman
     * 这样只有消息是携带路由键是topic.woman的才会分发到该队列
     *
     * @return
     */
    @Bean
    public Binding bindingWoman() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }

}
