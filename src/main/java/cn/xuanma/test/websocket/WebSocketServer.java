package cn.xuanma.test.websocket;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


/**
 * @Author:wangshu'an
 * @date:2022/11/17 14:02
 * @Description:
 */

@Component
@ServerEndpoint("/websocket/{token}")
public class WebSocketServer {
    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private static String userId;

    //连接时执行
    @OnOpen
    public void onOpen(@PathParam("userId") String userId,Session session) throws IOException {
        this.userId = userId;
        logger.debug("新连接：{}",userId);
    }

    //关闭时执行
    @OnClose
    public void onClose(){
        logger.debug("连接：{} 关闭",this.userId);
    }

    //收到消息时执行
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.debug("收到用户{}的消息{}",this.userId,message);
        session.getBasicRemote().sendText("收到 "+this.userId+" 的消息 "); //回复用户
    }

    //连接错误时执行
    @OnError
    public void onError(Session session, Throwable error){
        logger.debug("用户id为：{}的连接发送错误",this.userId);
        error.printStackTrace();
    }

}
