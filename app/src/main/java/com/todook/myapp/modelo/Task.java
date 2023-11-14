package com.todook.myapp.modelo;

public class Task {

    private int id;
    public int iduser;

    public String taskname;
    public String taskdate;
    public String timedate;
    public int type;

    public Task(int id, int iduser, String taskname, String taskdate, String timedate, int type) {
        this.id = id;
        this.iduser = iduser;
        this.taskname = taskname;
        this.taskdate = taskdate;
        this.timedate = timedate;
        this.type = type;
    }

    public Task() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getTaskdate() {
        return taskdate;
    }

    public void setTaskdate(String taskdate) {
        this.taskdate = taskdate;
    }

    public String getTimedate() {
        return timedate;
    }

    public void setTimedate(String timedate) {
        this.timedate = timedate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}