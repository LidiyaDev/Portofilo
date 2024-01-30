package com.dashenbank.mttodb.DTO;

public class MessageResponse {
    

    private String REFERENCE_NO;
    private String BRANCH;
    private String SENDER;
    private String VALUE_DATE;
    private String AMOUNT;
    private String MESSAGE;
    private String CCY;

    public MessageResponse(){
        
    }
    public MessageResponse(String rEFERENCE_NO, String bRANCH, String sENDER, String vALUE_DATE, String aMOUNT,
            String mESSAGE, String cCY) {
        REFERENCE_NO = rEFERENCE_NO;
        BRANCH = bRANCH;
        SENDER = sENDER;
        VALUE_DATE = vALUE_DATE;
        AMOUNT = aMOUNT;
        MESSAGE = mESSAGE;
        CCY = cCY;
    }
    public String getREFERENCE_NO() {
        return REFERENCE_NO;
    }
    public void setREFERENCE_NO(String rEFERENCE_NO) {
        REFERENCE_NO = rEFERENCE_NO;
    }
    public String getBRANCH() {
        return BRANCH;
    }
    public void setBRANCH(String bRANCH) {
        BRANCH = bRANCH;
    }
    public String getSENDER() {
        return SENDER;
    }
    public void setSENDER(String sENDER) {
        SENDER = sENDER;
    }
    public String getVALUE_DATE() {
        return VALUE_DATE;
    }
    public void setVALUE_DATE(String vALUE_DATE) {
        VALUE_DATE = vALUE_DATE;
    }
    public String getAMOUNT() {
        return AMOUNT;
    }
    public void setAMOUNT(String aMOUNT) {
        AMOUNT = aMOUNT;
    }
    public String getMESSAGE() {
        return MESSAGE;
    }
    public void setMESSAGE(String mESSAGE) {
        MESSAGE = mESSAGE;
    }
    public String getCCY() {
        return CCY;
    }
    public void setCCY(String cCY) {
        CCY = cCY;
    }


}
