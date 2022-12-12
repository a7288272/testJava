package cn.xuanma.test.config.sentinel;

import cn.xuanma.common.vo.RetResult;
import cn.xuanma.test.config.sentinel.enums.SentinelErrEnum;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * sentinel 监控 异常处理
 */
@Component
@Slf4j
public class SentinelExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        //getRule返回资源、规则的详细信息
        log.error("限流资源:"+ e.getRule().getResource() + ",msg:"+e.getMessage(),e);

        RetResult r = RetResult.failed(SentinelErrEnum.LIMIT_FLOW_HANDLE, null);
        if(e instanceof FlowException) {
            r = RetResult.failed(SentinelErrEnum.LIMIT_FLOW_HANDLE, null);
        }
        if (e instanceof DegradeException) {
            r = RetResult.failed(SentinelErrEnum.LIMIT_DOWN_HANDLE, null);
        }
        if (e instanceof ParamFlowException) {
            r = RetResult.failed(SentinelErrEnum.LIMIT_FLOW_PARAMS_HANDLE, null);
        }

        if (e instanceof AuthorityException) {
            r = RetResult.failed(SentinelErrEnum.LIMIT_AUTH_RULE_HANDLE, null);
        }

        //返回Json数据
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.getWriter().write(JSONObject.toJSONString(r));
    }
}
