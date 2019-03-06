package industries.dysl.com.notepad;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class NoteList {
    private List<Note> noteList;


    public boolean loadNote(Context context){
        return true;
    }

    public void saveNote(Context context) {
        File directory = new File(context.getFilesDir(), "notes");
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            String bodyOfText = "";
            File file = new File(directory, "NotesSave.bin");
            FileWriter writer = new FileWriter(file,false);
            writer.append(bodyOfText);
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
