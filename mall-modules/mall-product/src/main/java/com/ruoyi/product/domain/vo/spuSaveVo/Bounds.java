package com.ruoyi.product.domain.vo.spuSaveVo;

import java.math.BigDecimal;

public class Bounds {
    private static final long serialVersionUID = 1L;

    private BigDecimal buyBounds;
    private BigDecimal growBounds;

    public BigDecimal getBuyBounds() {
        return buyBounds;
    }

    public void setBuyBounds(BigDecimal buyBounds) {
        this.buyBounds = buyBounds;
    }

    public BigDecimal getGrowBounds() {
        return growBounds;
    }

    public void setGrowBounds(BigDecimal growBounds) {
        this.growBounds = growBounds;
    }
}
