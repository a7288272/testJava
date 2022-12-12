package cn.xuanma.test.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:wangshu'an
 * @date:2022/11/15 16:18
 * @Description:
 */

@Configuration
public class DirectRabbitMQConfig {
    /**
     * 队列 起名 testDirectQueue
     *
     * @return
     */
    @Bean
    public Queue testDirectQueue() {
        // durable 是否持久化
        return new Queue("testDirectQueue",true);
    }

    /**
     * Direct交换机  testDirectExchange
     *
     * @return
     */
    @Bean
    public DirectExchange testDirectExchange() {
        return new DirectExchange("testDirectExchange");
    }

    /**
     * 交换机未绑定队列
     * @return
     */
    @Bean
    public DirectExchange testDirectExchange2() {
        return new DirectExchange("testDirectExchangeNoQueue");
    }

    /**
     * 绑定 将队列和交换机绑定，并设置用于匹配键 testDirectRouting
     *
     * @return
     */
    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("testDirectRouting");
    }


}
