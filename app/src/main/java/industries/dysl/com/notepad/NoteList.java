package industries.dysl.com.notepad;

import android.content.Context;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NoteList {

    private static final String NOTE_NAME = "noteSave.bin";
    private static NoteList noteList;
    private List<Note> notes;

    private File directory;

    private NoteList() {
        notes = new ArrayList<Note>();
    }

    public static NoteList getNoteList() {
        if (noteList == null) {
            noteList = new NoteList();
        }
        return noteList;
    }

    public int getSize() {
        return notes.size();
    }

    public List<Note> getNotes() {
        return notes;
    }

    public boolean loadNote(Context context) {
        //Load note into here
        checkDir(context);
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(new File(directory, NOTE_NAME));
            ObjectInputStream oi = new ObjectInputStream(fi);
            notes = (List<Note>) oi.readObject();
            oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }


    private void checkDir(Context context) {
        directory = new File(context.getFilesDir(), "notes");
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public void updateNotes(Context context) {
        checkDir(context);
        try {
            System.out.println(directory);
            File file = new File(directory, NOTE_NAME);
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(notes);
            o.close();
            f.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void saveNote(Context context, Note note) {
        notes.add(note);
        updateNotes(context);
    }
}
