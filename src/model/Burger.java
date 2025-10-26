package model;
 
public class Burger {
    private String orderId;
    private String customerId;
    private String custName;
    private int qty;
    private double total;
    private int status;

    public Burger(String orderId, String customerId, String custName, int qty, double total, int status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.custName = custName;
        this.qty = qty;
        this.total = total;
        this.status = status;
    }

    public Burger() {
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @param custName the custName to set
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
    }


    public void setTotal(double total) {
        this.total = total;
    } 
    public void setStatus(int status) {
        this.status = status;
    } 
    public String getOrderId() {
        return orderId;
    } 
    public String getCustomerId() {
        return customerId;
    } 
    public String getCustName() {
        return custName;
    } 
    public int getQty() {
        return qty;
    } 
    public double getTotal() {
        return total;
    } 
    public int getStatus() {
        return status;
    }
    
    
}
