package cn.xuanma.test.config.sentinel.enums;

import cn.xuanma.common.enums.ErrorEnum;
import lombok.Getter;

/**
 * @description 业务错误枚举类
 * @author jiangjianhe
 * @date 2022-06-01
 */
@Getter
public enum SentinelErrEnum implements ErrorEnum {
    LIMIT_FLOW_HANDLE("2001","002","被限流了"),
    LIMIT_DOWN_HANDLE("2001","003","服务降级了"),
    LIMIT_FLOW_PARAMS_HANDLE("2001","004","热点参数限流了"),
    LIMIT_AUTH_RULE_HANDLE("2001","005","授权规则不通过"),

    ;

    private String  errorCode;
    private String  miniErrorCode;
    private String  msg;


    SentinelErrEnum(String errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.miniErrorCode = this.name();
    }

    SentinelErrEnum(String errorCode, String miniErrorCode,String msg) {
        this.errorCode = errorCode;
        this.msg       = msg;
        this.miniErrorCode = miniErrorCode;
    }

    public static SentinelErrEnum getByCode(String errorCode){
        for(SentinelErrEnum en : SentinelErrEnum.values()){
            String ecode = en.errorCode + en.miniErrorCode;
            if(ecode.equals(errorCode)){
                return en;
            }
        }

        return null;
    }
}
