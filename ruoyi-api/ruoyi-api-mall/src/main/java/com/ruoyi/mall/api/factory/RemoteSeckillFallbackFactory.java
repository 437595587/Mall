package com.ruoyi.mall.api.factory;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.exception.BizCodeEnum;
import com.ruoyi.mall.api.RemoteSeckillService;
import com.ruoyi.mall.api.vo.SeckillInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteSeckillFallbackFactory implements FallbackFactory<RemoteSeckillService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteSeckillFallbackFactory.class);

    @Override
    public RemoteSeckillService create(Throwable throwable) {
        log.error("秒杀服务调用失败:{}", throwable.getMessage());
        return new RemoteSeckillService()
        {
            @Override
            public R<SeckillInfoVo> getSkuSeckillInfo(Long skuId) {
                return R.fail(BizCodeEnum.TOO_MANY_REQUEST.getCode().intValue(), BizCodeEnum.TOO_MANY_REQUEST.getMsg());
            }
        };
    }
}
