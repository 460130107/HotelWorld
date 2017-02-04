package edu.nju.hotel.data.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by zhouxiaofan on 2017/2/4.
 */
@Entity
@Table(name = "bankCard")
public class BankCard {
    private String number;
    private Integer balance;
    private String name;
    private Collection<User> usersByNumber;

    @Id
    @Column(name = "number", nullable = false, length = 20)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "balance", nullable = true)
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankCard bankCard = (BankCard) o;

        if (number != null ? !number.equals(bankCard.number) : bankCard.number != null) return false;
        if (balance != null ? !balance.equals(bankCard.balance) : bankCard.balance != null) return false;
        if (name != null ? !name.equals(bankCard.name) : bankCard.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "bankCardByBank")
    public Collection<User> getUsersByNumber() {
        return usersByNumber;
    }

    public void setUsersByNumber(Collection<User> usersByNumber) {
        this.usersByNumber = usersByNumber;
    }
}
