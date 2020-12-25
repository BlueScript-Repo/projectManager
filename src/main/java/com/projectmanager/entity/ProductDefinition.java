package com.projectmanager.entity;

import javax.persistence.*;

@Entity
public class ProductDefinition {



    @EmbeddedId
    private ProductId productId;

    @GeneratedValue(strategy= GenerationType.AUTO)
    private  int id;
    private String classOrSch;
    private String materialSpecs;
    private String standardType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductId getProductId() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public String getClassOrSch() {
        return classOrSch;
    }

    public void setClassOrSch(String classOrSch) {
        this.classOrSch = classOrSch;
    }

    public String getMaterialSpecs() {
        return materialSpecs;
    }

    public void setMaterialSpecs(String materialSpecs) {
        this.materialSpecs = materialSpecs;
    }

    public String getStandardType() {
        return standardType;
    }

    public void setStandardType(String standardType) {
        this.standardType = standardType;
    }

    public ProductDefinition(){

    }
    public ProductDefinition(String product, String moc, String ct, String aNull, String s, String aNull1)
    {

    }

    public ProductDefinition(int id,ProductId productId, String classOrSch, String materialSpecs, String standardType) {
        this.id = id;
        this.productId = productId;
        this.classOrSch = classOrSch;
        this.materialSpecs = materialSpecs;
        this.standardType = standardType;
    }
}
