package com.kpi.model;

import lombok.Data;

import java.sql.Date;


/**
 * Created by gleb on 09.12.16.
 */
@Data
public class FamilyBudget {
    private Integer id;
    private Integer amountOfMoney;
    private Date dateOfOperation;
    private Integer familyMemberId;
    private Integer operationId;
}
