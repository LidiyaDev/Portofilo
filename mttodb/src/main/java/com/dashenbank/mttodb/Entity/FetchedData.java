package com.dashenbank.mttodb.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "FETCHED_DATA")

@NamedQueries({
    @NamedQuery(name = "FetchedData.findAll", query = "SELECT f FROM FetchedData f")
    , @NamedQuery(name = "FetchedData.findById", query = "SELECT f FROM FetchedData f WHERE f.id = :id")
    , @NamedQuery(name = "FetchedData.findByAccountNumber", query = "SELECT f FROM FetchedData f WHERE f.accountNumber = :accountNumber")
    , @NamedQuery(name = "FetchedData.findByIsDashenBranch", query = "SELECT f FROM FetchedData f WHERE f.isDashenBranch = :isDashenBranch")
    , @NamedQuery(name = "FetchedData.findByFcyAmount", query = "SELECT f FROM FetchedData f WHERE f.fcyAmount = :fcyAmount")
    , @NamedQuery(name = "FetchedData.findByOrderingCustomer", query = "SELECT f FROM FetchedData f WHERE f.orderingCustomer = :orderingCustomer")
    , @NamedQuery(name = "FetchedData.findByOtherBankBranch", query = "SELECT f FROM FetchedData f WHERE f.otherBankBranch = :otherBankBranch")
    , @NamedQuery(name = "FetchedData.findByReference", query = "SELECT f FROM FetchedData f WHERE f.reference = :reference")
    , @NamedQuery(name = "FetchedData.findByRegistrationDate", query = "SELECT f FROM FetchedData f WHERE f.registrationDate = :registrationDate")
    , @NamedQuery(name = "FetchedData.findBySenderBank", query = "SELECT f FROM FetchedData f WHERE f.senderBank = :senderBank")
    , @NamedQuery(name = "FetchedData.findByValueDate", query = "SELECT f FROM FetchedData f WHERE f.valueDate = :valueDate")
    , @NamedQuery(name = "FetchedData.findByBeneficiary", query = "SELECT f FROM FetchedData f WHERE f.beneficiary = :beneficiary")
    , @NamedQuery(name = "FetchedData.findByDashenBranch", query = "SELECT f FROM FetchedData f WHERE f.dashenBranch = :dashenBranch")
    , @NamedQuery(name = "FetchedData.findByCorrespondentBank", query = "SELECT f FROM FetchedData f WHERE f.correspondentBank = :correspondentBank")
    , @NamedQuery(name = "FetchedData.findByCountryOfOrigin", query = "SELECT f FROM FetchedData f WHERE f.countryOfOrigin = :countryOfOrigin")
    , @NamedQuery(name = "FetchedData.findByCreatedBy", query = "SELECT f FROM FetchedData f WHERE f.createdBy = :createdBy")
    , @NamedQuery(name = "FetchedData.findByCurrencyId", query = "SELECT f FROM FetchedData f WHERE f.currencyId = :currencyId")
    , @NamedQuery(name = "FetchedData.findByPaymentPurpose", query = "SELECT f FROM FetchedData f WHERE f.paymentPurpose = :paymentPurpose")
    , @NamedQuery(name = "FetchedData.findByModifiedBy", query = "SELECT f FROM FetchedData f WHERE f.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "FetchedData.findByModifiedDate", query = "SELECT f FROM FetchedData f WHERE f.modifiedDate = :modifiedDate")
    , @NamedQuery(name = "FetchedData.findByDeletedBy", query = "SELECT f FROM FetchedData f WHERE f.deletedBy = :deletedBy")
    , @NamedQuery(name = "FetchedData.findByDeletedDate", query = "SELECT f FROM FetchedData f WHERE f.deletedDate = :deletedDate")})
public class FetchedData implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FETCHED_SEQ")
    @SequenceGenerator(name = "FETCHED_SEQ", sequenceName = "FETCHED_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private BigDecimal id;
    //@Size(max = 255)
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @Column(name = "IS_DASHEN_BRANCH")
    private Character isDashenBranch;
    @Column(name = "FCY_AMOUNT")
    private Double fcyAmount;
    //@Size(max = 255)
    @Column(name = "ORDERING_CUSTOMER")
    private String orderingCustomer;
    //@Size(max = 255)
    @Column(name = "OTHER_BANK_BRANCH")
    private String otherBankBranch;
    //@Size(max = 255)
    @Column(name = "REFERENCE")
    private String reference;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "REGISTRATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    //@Size(max = 255)
    @Column(name = "SENDER_BANK")
    private String senderBank;
    @Column(name = "VALUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valueDate;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 2000)
    @Column(name = "BENEFICIARY")
    private String beneficiary;
    //@Size(max = 2000)
    @Column(name = "DASHEN_BRANCH")
    private String dashenBranch;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 2000)
    @Column(name = "CORRESPONDENT_BANK")
    private String correspondentBank;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 2000)
    @Column(name = "COUNTRY_OF_ORIGIN")
    private String countryOfOrigin;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 20)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 20)
    @Column(name = "CURRENCY_ID")
    private String currencyId;
    //@Size(max = 255)
    @Column(name = "PAYMENT_PURPOSE")
    private String paymentPurpose;
    //@Size(max = 120)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    //@Size(max = 120)
    @Column(name = "DELETED_BY")
    private String deletedBy;
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    public FetchedData() {
    }

    public FetchedData(BigDecimal id) {
        this.id = id;
    }

    public FetchedData(BigDecimal id, Date registrationDate, String beneficiary, String correspondentBank, String countryOfOrigin, String createdBy, String currencyId) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.beneficiary = beneficiary;
        this.correspondentBank = correspondentBank;
        this.countryOfOrigin = countryOfOrigin;
        this.createdBy = createdBy;
        this.currencyId = currencyId;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FetchedData)) {
            return false;
        }
        FetchedData other = (FetchedData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FetchedData[ id=" + id + " ]";
    }
    
}
