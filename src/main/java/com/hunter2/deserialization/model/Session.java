package com.hunter2.deserialization.model;

import java.io.Serializable;

public class Session implements Serializable{
    private static final long serialVersionUID=42L;
    private String sessionId;

    public Session(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId='" + sessionId + '\'' +
                '}';
    }
}