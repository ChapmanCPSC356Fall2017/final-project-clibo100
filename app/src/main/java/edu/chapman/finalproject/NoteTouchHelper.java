package edu.chapman.finalproject;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

public class NoteTouchHelper extends ItemTouchHelper.SimpleCallback {
    private static final String TAG = "notetouchhelper";
    private NotesListAdapter listAdapter;

    //sets up the directions you can drag
    NoteTouchHelper(NotesListAdapter listAdapter){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.listAdapter = listAdapter;
        Log.d(TAG, "notetouchhelper constructor");
    }

    @Override
    //swaps if items are moved
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.d(TAG, "notetouchhelper onmove");
        listAdapter.swap(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    //removes if items are swiped off screen
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.d(TAG, "notetouchhelper onswiped");
        listAdapter.remove(viewHolder.getAdapterPosition());
    }
}