package industries.dysl.com.notepad;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements Serializable {
    private String title;
    private String note;
    private long creation;


    public Note(String title, String note) {
        this.title = title;
        this.note = note;
        updateCreation();

    }
    private void updateCreation(){
        creation = System.currentTimeMillis();
    }

    public void updateNote(String title, String note) {
        this.title = title;
        this.note = note;
        updateCreation();
    }


    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public long getCreation() {
        return creation;
    }

    //Taken from https://stackoverflow.com/questions/6782185/convert-timestamp-long-to-normal-date-format
    public String getDateString() {
        Date date = new Date(creation);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(date);
    }
}

