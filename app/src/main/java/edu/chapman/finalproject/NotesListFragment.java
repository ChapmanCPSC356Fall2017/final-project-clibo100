package edu.chapman.finalproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NotesListFragment extends Fragment{
    private static final String LOGTAG = "NotesListFragment";
    private NotesListAdapter adapter;

    @Nullable
    @Override
    //inflates view and calls up the swiping and swapping stuff
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        Log.d(LOGTAG, "onCreateView()");
        View v = inflater.inflate(R.layout.fragment_noteslist, container, false);

        RecyclerView ListView = v.findViewById(R.id.rv_list_elements);
        this.adapter = new NotesListAdapter();
        ListView.setAdapter(adapter);

        // according to ryan this is lame and i shouldn't study it i should just do it
        ListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper.Callback callback = new NoteTouchHelper(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(ListView);

        return v;
    }

    @Override
    //changes data if it is notified to
    public void onResume()
    {
        Log.d(LOGTAG, "OnResume()");
        super.onResume();

        this.adapter.notifyDataSetChanged();
    }
}
