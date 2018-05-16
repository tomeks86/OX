package edu.tseidler.model;

public class Choice {
    private int selection;
    private boolean valid;

    public Choice() {
        this.selection = -1;
        this.valid = false;
    }

    public Choice(int selection) {
        this.selection = selection;
        this.valid = false;
    }

    public int getSelection() {
        return selection;
    }

    public boolean isValid() {
        return valid;
    }

    void setValid() {
        valid = true;
    }
}
