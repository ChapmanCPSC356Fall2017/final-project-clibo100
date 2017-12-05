package edu.chapman.finalproject;


        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.helper.ItemTouchHelper;

public class NoteTouchHelper extends ItemTouchHelper.SimpleCallback {
    private NotesListAdapter listAdapter;

    //sets up the directions you can drag
    NoteTouchHelper(NotesListAdapter listAdapter){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.listAdapter = listAdapter;
    }

    @Override
    //swaps if items are moved
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        listAdapter.swap(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    //removes if items are swiped off screen
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listAdapter.remove(viewHolder.getAdapterPosition());
    }
}