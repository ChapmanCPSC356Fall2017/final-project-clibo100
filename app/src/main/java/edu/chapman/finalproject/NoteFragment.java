package edu.chapman.finalproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        TextView titleTextView = v.findViewById(R.id.tv_title);
        titleTextView.setText(this.note.getTitle());

        TextView descriptionTextView = v.findViewById(R.id.tv_description);
        descriptionTextView.setText(this.note.getBody());

        titleTextView.addTextChangedListener(new TextWatcher()
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

        descriptionTextView.addTextChangedListener(new TextWatcher()
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
