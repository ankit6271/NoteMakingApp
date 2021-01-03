package com.example.notemakingapp.Room;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Note")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String price;

    private String description;

    private int priority;

    public Note(String price, String description, int priority) {
        this.price = price;
        this.description = description;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public void setId(int id) {
        this.id = id;
    }
}
