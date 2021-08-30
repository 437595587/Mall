package com.ruoyi.product.domain.vo;

import java.util.List;

//2级分类
public class Catelog2Vo {
    private String catalog1Id; //1级父分类
    private List<Catelog3Vo> catalog3List; //3级子分类
    private String id;
    private String name;

    //三级分类
    public static class Catelog3Vo {
        private String catalog2Id;
        private String id;
        private String name;

        public Catelog3Vo() {
        }

        public Catelog3Vo(String catalog2Id, String id, String name) {
            this.catalog2Id = catalog2Id;
            this.id = id;
            this.name = name;
        }

        public String getCatalog2Id() {
            return catalog2Id;
        }

        public void setCatalog2Id(String catalog2Id) {
            this.catalog2Id = catalog2Id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getCatalog1Id() {
        return catalog1Id;
    }

    public void setCatalog1Id(String catalog1Id) {
        this.catalog1Id = catalog1Id;
    }

    public List<Catelog3Vo> getCatalog3List() {
        return catalog3List;
    }

    public void setCatalog3List(List<Catelog3Vo> catalog3List) {
        this.catalog3List = catalog3List;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Catelog2Vo() {
    }

    public Catelog2Vo(String catalog1Id, List<Catelog3Vo> catalog3List, String id, String name) {
        this.catalog1Id = catalog1Id;
        this.catalog3List = catalog3List;
        this.id = id;
        this.name = name;
    }
}
