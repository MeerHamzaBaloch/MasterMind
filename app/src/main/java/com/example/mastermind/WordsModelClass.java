package com.example.mastermind;

public class WordsModelClass {
    Integer id;
    String name;
    String type;

    public WordsModelClass(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public WordsModelClass(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
