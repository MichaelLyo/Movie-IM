package com.tongji.movie.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="LanguageDim")
public class LanguageDim {
    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String languageId;

    private String languageName;

    private String type;
}
