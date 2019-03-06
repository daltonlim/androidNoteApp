package industries.dysl.com.notepad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class CreateNote extends AppCompatActivity {
    private EditText titleBox;
    private EditText contentBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        titleBox = (EditText) findViewById(R.id.titleBox);
        contentBox = (EditText) findViewById(R.id.contentBox);

    }

    private boolean saveNote() {
        Note note = new Note(titleBox.getText().toString(), new NormalContent(contentBox.getText().toString()),
                System.currentTimeMillis());
        return true;
    }

}
