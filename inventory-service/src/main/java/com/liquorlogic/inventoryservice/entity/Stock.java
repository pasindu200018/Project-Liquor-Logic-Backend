package com.liquorlogic.inventoryservice.entity;

import com.liquorlogic.inventoryservice.entity.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;


@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STOCK_ID")
    private UUID stockId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPPLIER_ID")
    private Supplier supplier;

    @Column(name = "BRANDID")
    private UUID brandId;
    @Column(name = "DISCRIPTION")
    private String discription;
    @Column(name = "IMAGE")
    private String image;
    @Column(name = "QTY")
    private int QTY;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "UPDATE_BY")
    private String updateBy;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
    @Column(name = "STATUS")
    private Status status;

}
