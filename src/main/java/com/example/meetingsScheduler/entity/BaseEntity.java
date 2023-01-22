package com.example.meetingsScheduler.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @CreatedBy
    @Column(nullable = true)
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime creationDate;

    @LastModifiedDate
    private LocalDateTime modificationDate;

    @PrePersist
    public void onCreate() {
        if (creationDate == null) {
            creationDate = LocalDateTime.now();
        }

        if (createdBy == null) {
            // TODO createdBy
        }
    }

    @PreUpdate
    public void onUpdate() {
        modificationDate = LocalDateTime.now();
        // TODO modifiedBy
    }


}
