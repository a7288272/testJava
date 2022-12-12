package cn.xuanma.test.config.sentinel;

import cn.xuanma.common.vo.RetResult;
import cn.xuanma.test.config.sentinel.enums.SentinelErrEnum;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring configuration for global exception handler.
 * This will be activated when the {@code BlockExceptionHandler}
 * throws {@link BlockException directly}.
 *
 * @author kaizi2009
 */
@ControllerAdvice
@Order(0)
@Slf4j
public class SentinelSpringMvcBlockHandlerConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BlockException.class)
    @ResponseBody
    public RetResult sentinelBlockHandler(BlockException e) {

        log.error("限流了，限流资源:"+ e.getRule().getResource() + ",msg:"+e.getMessage(),e);

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

        return r;
    }
}
