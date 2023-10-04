package com.usertracapi.constants;

public enum Status {
    
    PENDENTE("pendente"),
    CONCLUIDO("concluído"),
    USER("user"),
    ADMIN("admin");

    private String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    
}
