package com.ruoyi.ware.domain.vo;

import java.util.List;

public class MergeVo {
    private Long purchaseId;
    private List<Long> items;

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public List<Long> getItems() {
        return items;
    }

    public void setItems(List<Long> items) {
        this.items = items;
    }
}
