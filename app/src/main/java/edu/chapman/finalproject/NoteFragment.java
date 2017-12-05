package edu.chapman.finalproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NoteFragment extends Fragment {
    private NoteModel note;

    @Override
    //gets all the info from the notedescriptionactivity
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        String listElementId = getArguments().getString(NoteDescriptionActivity.EXTRA_NOTE_ID);
        this.note = NoteCollection.GetInstance().getListElement(listElementId);
    }

    @Nullable
    @Override

    //inflates view, sets text of stuff, etc
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_notedescription, container, false);

        final EditText titleEditText = v.findViewById(R.id.et_title);
        titleEditText.setText(this.note.getTitle());

        final EditText bodyEditText = v.findViewById(R.id.et_description);
        bodyEditText.setText(this.note.getBody());

        Button saveButton = v.findViewById(R.id.bt_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                note.setTitle(titleEditText.getEditableText().toString());
                note.setBody(bodyEditText.getEditableText().toString());
                Toast.makeText(getContext(), "Note Saved", Toast.LENGTH_SHORT).show();
            }});

        return v;
    }

    public void onClick(View v) {

    }
}
