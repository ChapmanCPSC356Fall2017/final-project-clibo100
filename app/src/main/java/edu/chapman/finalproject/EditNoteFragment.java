package edu.chapman.finalproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditNoteFragment extends Fragment{
    private NoteModel note;

    @Override
    //gets all the info from the editnoteactivity
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        String listElementId = getArguments().getString(EditNoteActivity.EXTRA_EDITNOTE_ID);
        this.note = NoteCollection.GetInstance().getListElement(listElementId);
    }

    @Nullable
    @Override

    //inflates view, sets text of stuff, etc
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_editnote, container, false);

        EditText titleEditText = v.findViewById(R.id.et_title);
        titleEditText.setText(this.note.getTitle());

        EditText descriptionEditText = v.findViewById(R.id.et_description);
        descriptionEditText.setText(this.note.getBody());

        titleEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                note.setTitle(editable.toString());
            }
        });

        descriptionEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                note.setBody(editable.toString());
            }
        });

        return v;
    }
}
