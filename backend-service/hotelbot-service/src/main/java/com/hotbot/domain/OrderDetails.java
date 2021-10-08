package com.hotbot.domain;

import com.hotbot.model.customerapplication.Items;

import java.util.Date;
import java.util.List;

public class OrderDetails {

    private List<Items> items;
    private String tableNo;
    private String deviceNo;
    private Date starTime;
    private boolean inProgress;
    private boolean inWaitingList;
    private boolean isDone;
}
