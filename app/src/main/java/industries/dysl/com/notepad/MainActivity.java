package industries.dysl.com.notepad;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView _noteList;
    private final static int LENGTH = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _noteList = (ListView) findViewById(R.id._noteList);
        NoteList.getNoteList().loadNote(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_new_note:
                Intent newActivity  = new Intent(this, NoteEditor.class);
                startActivity(newActivity);
                break;
        }
        return true;

    }

    @Override
    protected void onResume() {
        super.onResume();
        final List<Note> noteList = NoteList.getNoteList().getNotes();
        _noteList.setAdapter(new ListAdapter() {
            List<Note> noteList = NoteList.getNoteList().getNotes();
            @Override
            public boolean areAllItemsEnabled() {
                return true;
            }

            @Override
            public boolean isEnabled(int i) {
                return true;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return noteList.size();
            }

            @Override
            public Object getItem(int i) {
                return noteList.get(i);
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view == null){
                    view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_note,null);
                }
                Note note = noteList.get(i);

                if(note != null){
                    TextView title = (TextView) view.findViewById(R.id.textView_title);
                    TextView content = (TextView) view.findViewById(R.id.textView_note);
                    TextView date = (TextView) view.findViewById(R.id.textView_date);

                    title.setText(note.getTitle());
                    date.setText(note.getDateString());
                    content.setText(note.getNote());
                    if (content.getText().length() > LENGTH){
                        content.setText(note.getNote().substring(0,LENGTH));
                    }



                }
                return view;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });

        if(noteList == null || noteList.isEmpty()){
            Toast.makeText(this,"No notes could be found",Toast.LENGTH_LONG).show();

        }
        _noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Note note = (Note) _noteList.getItemAtPosition(i);
                //getLayoutInflater().inflate(R.layout.activity_create_note,null);
                Intent viewNoteIntent = new Intent(getApplicationContext(),NoteEditor.class);
                viewNoteIntent.putExtra("Note",note);
                viewNoteIntent.putExtra("Index",i);
                startActivity(viewNoteIntent);

            }
        });
    }
}
