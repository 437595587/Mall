package com.ruoyi.mall.common.core.to.mq;

import lombok.Data;

import java.util.List;

@Data
public class StockLockedTo {
    //库存工作单id
    private Long id;
    //工作单详情id
    private StockDetailTo detailTo;
}
