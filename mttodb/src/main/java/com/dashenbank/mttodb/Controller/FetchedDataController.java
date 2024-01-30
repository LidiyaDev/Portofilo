package com.dashenbank.mttodb.Controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.dashenbank.mttodb.DTO.MessageResponse;
import com.dashenbank.mttodb.Entity.FetchedData;
import com.dashenbank.mttodb.Service.FetchMessageService;
import com.dashenbank.mttodb.Service.FetchedDataService;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field32A;
import com.prowidesoftware.swift.model.field.Field50F;
import com.prowidesoftware.swift.model.field.Field50K;
import com.prowidesoftware.swift.model.field.Field53A;
import com.prowidesoftware.swift.model.field.Field54A;
import com.prowidesoftware.swift.model.field.Field57A;
import com.prowidesoftware.swift.model.field.Field59;
import com.prowidesoftware.swift.model.field.Field59F;
import com.prowidesoftware.swift.model.field.Field70;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class FetchedDataController {

    @Autowired
    FetchedDataService fetchedDataService;

    @Autowired
    FetchMessageService fetchMessageService;

    public String message = new String();
    private String accountNumber;
    private Character isDashenBranch;
    private Double fcyAmount;
    private String orderingCustomer;
    private String otherBankBranch;
    private String reference;
    private Date registrationDate;
    private String senderBank;
    private Date valueDate;
    private String beneficiary;
    private String dashenBranch;
    private String correspondentBank;
    private String countryOfOrigin;
    private String createdBy;
    private String currencyId;
    private String paymentPurpose;
    private String modifiedBy;
    private String deletedBy;
    private Date deletedDate;

    @GetMapping("/getMessage/")
    public String getAllmess() throws ParseException {

        fetchedDataService.deleteMessage();
        List<MessageResponse> response = new ArrayList<>();
        response = fetchMessageService.getMessageInfo();


        if (response != null) {
            for (MessageResponse messageResponse : response) {
                message = messageResponse.getMESSAGE();
            System.out.println("Response ======="+messageResponse.getMESSAGE());
            if (messageResponse.getMESSAGE().contains("$")) {
                
                String[] messageParts = message.split("\\$");

                for (String string : messageParts) {
                    String subMessage = removeSubstring(string, 0, 56);

                    converMessagetoParts(subMessage);
                    saveFethedData();
                }

            } else {
                System.out.println("Response ======="+messageResponse.getMESSAGE());
                String subMessage = removeSubstring(messageResponse.getMESSAGE(), 0, 56);

                converMessagetoParts(subMessage);
                saveFethedData();
            }
            }
            

            
        }

        return message;
    }



    @GetMapping("/getAllMessage/{ref}")
    public String getMethodName(@PathVariable String ref) throws ParseException {

        System.out.println("reference======"+ref);
        MessageResponse response = new MessageResponse();
        response = fetchMessageService.getMessageInfo(ref);


        if (response != null) {
            message = response.getMESSAGE();
            System.out.println("Response ======="+response.getMESSAGE());
            if (response.getMESSAGE().contains("$")) {
                
                String[] messageParts = message.split("\\$");

                for (String string : messageParts) {
                    String subMessage = removeSubstring(string, 0, 56);

                    converMessagetoParts(subMessage);
                    saveFethedData();
                }

            } else {
                System.out.println("Response ======="+response.getMESSAGE());
                String subMessage = removeSubstring(response.getMESSAGE(), 0, 56);

                converMessagetoParts(subMessage);
                saveFethedData();
            }

            
        }

        return message;
    }

    private void saveFethedData() {
        FetchedData data = new FetchedData();
        data.setAccountNumber(accountNumber);
        data.setBeneficiary(beneficiary);
        data.setCorrespondentBank(correspondentBank);
        data.setCountryOfOrigin(countryOfOrigin);
        data.setCreatedBy("Admin User");
        data.setCurrencyId(currencyId);
        data.setDashenBranch(dashenBranch);
        data.setDeletedBy(deletedBy);
        data.setDeletedDate(deletedDate);
        data.setFcyAmount(fcyAmount);
        data.setIsDashenBranch(isDashenBranch);
        data.setOrderingCustomer(orderingCustomer);
        data.setOtherBankBranch(otherBankBranch);
        data.setPaymentPurpose(paymentPurpose);
        data.setSenderBank(senderBank);
        data.setValueDate(valueDate);
        data.setReference(reference);
        data.setRegistrationDate(new Date());

        fetchedDataService.saveMessage(data);
    }

    private void converMessagetoParts(String subMessage) throws ParseException {
        MT103 m = MT103.parse(subMessage);

        Field20 referencem = m.getField20();
        if(referencem != null)
        {
            reference = referencem.getComponent1();
        }

        Field59 account = m.getField59();

        if (account != null) {

            accountNumber = account.getComponent1();

            beneficiary = account.getComponent2();
            
 
        } else {

            Field59F accountF = m.getField59F();

            if (accountF != null) {

                accountNumber = accountF.getComponent1();

                beneficiary = accountF.getComponent3();

            }

        }

        Field32A amount = m.getField32A();
        if (amount != null) {
            System.out.println("Amount component=======" + amount);

            DecimalFormat decimalFormat = new DecimalFormat("#,#00.0");
            fcyAmount = decimalFormat.parse(amount.getComponent3()).doubleValue();

            String am = amount.getComponent3().replace(",", ".");

            System.out.println("test am======"+am);

            Double amdouble = Double.valueOf(am);
            System.out.println("amdouble============"+amdouble);
            String date = amount.getComponent1();

            fcyAmount = amdouble;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
            LocalDate localDate = LocalDate.parse(date, formatter);
            Date value = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            valueDate = value;
            currencyId = amount.getComponent2();
        }

        Field50K ordering = m.getField50K();

        if (ordering != null) {

            orderingCustomer = ordering.getComponent2();

            countryOfOrigin = ordering.getComponent3();

            System.out.println(countryOfOrigin + " test country origin");

        } else {

            Field50F orderingF = m.getField50F();

            if (orderingF != null) {

                orderingCustomer = orderingF.getComponent3();

                countryOfOrigin = orderingF.getComponent5();

            }

            else {

                orderingCustomer = "";

                countryOfOrigin = "No country";

            }

        }

        Field54A coree = m.getField54A();

        if (coree != null) {

            correspondentBank = coree.getComponent3();

            System.out.println(coree);

        }

        else if (coree == null) {

            Field53A corre1 = m.getField53A();

            if (corre1 != null) {

                correspondentBank = corre1.getComponent3();

            }

            else if (m.getSender() != null) {

                correspondentBank = m.getSender();

            }

            else

            {

                correspondentBank = new String();

            }

        }

        Field57A otherbank = m.getField57A();
        if (otherbank != null) {
            System.out.println("other bank =========" + otherbank);
            otherBankBranch = otherbank.getComponent3();
        }

        senderBank = m.getSender();
        Field70 purpose = m.getField70();

        if (purpose != null) {

            System.out.println(purpose.getComponent1()
                    + "purpose payment");

            paymentPurpose = purpose.getComponent1();

        }
    }

    static String removeSubstring(String text, int startIndex, int endIndex) {

        if (endIndex < startIndex) {

            startIndex = endIndex;

        }

        String a = text.substring(0,
                startIndex);

        String b = text.substring(endIndex);

        return a + b;

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Character getIsDashenBranch() {
        return isDashenBranch;
    }

    public void setIsDashenBranch(Character isDashenBranch) {
        this.isDashenBranch = isDashenBranch;
    }

    public Double getFcyAmount() {
        return fcyAmount;
    }

    public void setFcyAmount(Double fcyAmount) {
        this.fcyAmount = fcyAmount;
    }

    public String getOrderingCustomer() {
        return orderingCustomer;
    }

    public void setOrderingCustomer(String orderingCustomer) {
        this.orderingCustomer = orderingCustomer;
    }

    public String getOtherBankBranch() {
        return otherBankBranch;
    }

    public void setOtherBankBranch(String otherBankBranch) {
        this.otherBankBranch = otherBankBranch;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getSenderBank() {
        return senderBank;
    }

    public void setSenderBank(String senderBank) {
        this.senderBank = senderBank;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getDashenBranch() {
        return dashenBranch;
    }

    public void setDashenBranch(String dashenBranch) {
        this.dashenBranch = dashenBranch;
    }

    public String getCorrespondentBank() {
        return correspondentBank;
    }

    public void setCorrespondentBank(String correspondentBank) {
        this.correspondentBank = correspondentBank;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

}
