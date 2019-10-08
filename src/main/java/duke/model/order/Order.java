package duke.model.order;

import duke.model.commons.Product;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import static duke.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents an order in order list.
 */
public class Order {

    //Identity field
    private final long id;
    //Data fields
    private final Customer customer;
    private final Date deliveryDate;
    private final Map<Product, Integer> items;
    private final String remarks;
    private final Status status;

    /**
     * Creates an order.
     * Every field must be present and not null.
     */
    public Order(Customer customer, Date deliveryDate, Status status, String remarks, Map<Product, Integer> items) {
        requireAllNonNull(customer, deliveryDate, status, remarks, items);

        this.customer = customer;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.remarks = remarks;
        this.id = System.currentTimeMillis();
        this.items = items;
    }

    public enum Status {
        ACTIVE,
        COMPLETED,
        CANCELED
    }

    public Customer getCustomer() {
        return customer;
    }

    public long getId() {
        return id;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public String getRemarks() {
        return remarks;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("ID: %s Customer: [%s] Date: %s Status: %s Items: %s",
                id, customer, deliveryDate, status, items);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}