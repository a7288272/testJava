package cn.xuanma.test.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:wangshu'an
 * @date:2022/11/15 17:15
 * @Description:
 */
@Configuration
public class FanoutRabbitMQConfig {
    @Bean
    public Queue queueA(){
        return new Queue("fanoutA");
    }
    @Bean
    public Queue queueB(){
        return new Queue("fanoutB");
    }
    @Bean
    public Queue queueC(){
        return new Queue("fanoutC");
    }
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }
    @Bean
    public Binding bindingToA(){
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingToB(){
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingToC(){
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }

}
