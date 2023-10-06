package com.example.poormusic.entity;

import jakarta.persistence.MappedSuperclass;

import java.sql.Timestamp;

@MappedSuperclass
public abstract class BaseEntity {

    protected Timestamp createOn;

    protected Timestamp updateOn;


}


