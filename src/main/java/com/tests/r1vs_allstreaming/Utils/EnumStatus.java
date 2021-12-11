package com.tests.r1vs_allstreaming.Utils;

public enum EnumStatus {
    AVAILABLE(1L),
    IN_USE(2L),
    BLOCKED(3L);

    private Long statusId;

    EnumStatus(Long statusId) {
        this.statusId = statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getStatusId() {
        return statusId;
    }
}
