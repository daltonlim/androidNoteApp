package industries.dysl.com.notepad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NoteEditor extends AppCompatActivity {
    private EditText titleBox;
    private EditText contentBox;
    private TextView textview_lastEdit;
    private Button button_save;
    private Button button_delete;
    private boolean oldNote = false;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        titleBox = (EditText) findViewById(R.id.titleBox);
        contentBox = (EditText) findViewById(R.id.contentBox);
        textview_lastEdit = (TextView) findViewById(R.id.textView_lastEdit);
        note = (Note) getIntent().getSerializableExtra("Note");
        if (note != null) {
            loadNote();
        }

        button_save = (Button) findViewById(R.id.button_save);
        button_delete = (Button) findViewById(R.id.button_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNote();
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });

    }

    public void loadNote() {
        oldNote = true;
        //TODO rememebr to delete note when loading editor, adn load in new note, only if changes
        titleBox.setText(note.getTitle());
        contentBox.setText(note.getNote());
        textview_lastEdit.setText("Last edited at: " + note.getDateString());

    }

    private void deleteNote() {

        List<Note> notes =  NoteList.getNoteList().getNotes();
        if (oldNote) {
           int index =  (int) getIntent().getSerializableExtra("Index");
           notes.remove(index);
            NoteList.getNoteList().updateNotes(this);
        }
        Toast.makeText(this, "Successfully saved " + NoteList.getNoteList().getSize() + " Objects",
                Toast.LENGTH_LONG).show();

        finish();
    }

    private boolean saveNote() {
        Note note = new Note( titleBox.getText().toString(),
                contentBox.getText().toString());
        NoteList.getNoteList().saveNote(this, note);
        deleteNote();
        return true;
    }

}
