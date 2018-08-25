package hbi.demo.dto;

/**Auto Generated By Hap Code Generator**/
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hand.hap.core.annotation.Children;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@ExtensionAttribute(disable=true)
@Table(name = "hap_om_order_headers")
public class OmOrderHeaders extends BaseDTO {

     public static final String FIELD_HEADER_ID = "headerId";
     public static final String FIELD_ORDER_NUMBER = "orderNumber";
     public static final String FIELD_COMPANY_ID = "companyId";
     public static final String FIELD_ORDER_DATE = "orderDate";
     public static final String FIELD_ORDER_STATUS = "orderStatus";
     public static final String FIELD_CUSTOMER_ID = "customerId";


     @Id
     @GeneratedValue
     private Long headerId; //订单头ID

     @NotEmpty
     @Length(max = 60)
     private String orderNumber; //订单编号

     @NotNull
     private Long companyId; //公司ID

     @NotEmpty
     @Length(max = 60)
     @Transient
     private String companyName; //公司全称

     @JsonFormat(pattern = "yyyy-MM-dd")
     @DateTimeFormat(pattern = "yyyy-MM-dd")
     private Date orderDate; //订单日期

     @NotEmpty
     @Length(max = 30)
     private String orderStatus; //订单状态

     @NotNull
     private Long customerId; //客户ID

     @NotNull
     @Transient
     private String customerName; // 客户名称

     @NotNull
     @Transient
     private Long orderAmount; //订单金额

     @NotNull
     @Transient
     private Long inventoryItemId; // 物料Id

     @NotEmpty
     @Length(max = 60)
     @Transient
     private String itemCode; //物料编码

     @NotEmpty
     @Length(max = 240)
     @Transient
     private String itemDescription; //物料描述

     @NotNull
     @Transient
     private Long orderdQuantity; //数量

     @NotNull
     @Transient
     private Long unitSellingPrice; //销售单价

     @Children
     @Transient
     private List<OmOrderLines> omOrderLinesList; // 订单行集合

     public void setHeaderId(Long headerId){
         this.headerId = headerId;
     }

     public Long getHeaderId(){
         return headerId;
     }

     public void setOrderNumber(String orderNumber){
         this.orderNumber = orderNumber;
     }

     public String getOrderNumber(){
         return orderNumber;
     }

     public void setCompanyId(Long companyId){
         this.companyId = companyId;
     }

     public Long getCompanyId(){
         return companyId;
     }

     public void setOrderDate(Date orderDate){
         this.orderDate = orderDate;
     }

     public Date getOrderDate(){
         return orderDate;
     }

     public void setOrderStatus(String orderStatus){
         this.orderStatus = orderStatus;
     }

     public String getOrderStatus(){
         return orderStatus;
     }

     public void setCustomerId(Long customerId){
         this.customerId = customerId;
     }

     public Long getCustomerId(){
         return customerId;
     }

     public Long getOrderAmount() {
         return orderAmount;
     }

     public void setOrderAmount(Long orderAmount) {
         this.orderAmount = orderAmount;
     }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OmOrderLines> getOmOrderLinesList() {
        return omOrderLinesList;
    }

    public void setOmOrderLinesList(List<OmOrderLines> omOrderLinesList) {
        this.omOrderLinesList = omOrderLinesList;
    }

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Long inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Long getOrderdQuantity() {
        return orderdQuantity;
    }

    public void setOrderdQuantity(Long orderdQuantity) {
        this.orderdQuantity = orderdQuantity;
    }

    public Long getUnitSellingPrice() {
        return unitSellingPrice;
    }

    public void setUnitSellingPrice(Long unitSellingPrice) {
        this.unitSellingPrice = unitSellingPrice;
    }


    @Override
    public String toString() {
        return "OmOrderHeaders{" +
                "headerId=" + headerId +
                ", orderNumber='" + orderNumber + '\'' +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", orderDate=" + orderDate +
                ", orderStatus='" + orderStatus + '\'' +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", orderAmount=" + orderAmount +
                ", inventoryItemId=" + inventoryItemId +
                ", omOrderLinesList=" + omOrderLinesList +
                '}';
    }
}
