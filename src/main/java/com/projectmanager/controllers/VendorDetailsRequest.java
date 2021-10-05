package com.projectmanager.controllers;

public class VendorDetailsRequest {

    private String vendorName;
    private String vendorAddress;
    private String contactName;
    private String contactEmail;
    private String contactNumber;
    private String vendorGst;
    private String vendorPan;

    public VendorDetailsRequest() {
    }

    public String getVendorName() {
        return vendorName;
    }
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    public String getVendorAddress() {
        return vendorAddress;
    }
    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getVendorGst() {
        return vendorGst;
    }



    public void setVendorGst(String vendorGst) {
        this.vendorGst = vendorGst;
    }



    public String getVendorPan() {
        return vendorPan;
    }



    public void setVendorPan(String vendorPan) {
        this.vendorPan = vendorPan;
    }


}
