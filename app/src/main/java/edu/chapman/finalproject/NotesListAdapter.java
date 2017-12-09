package edu.chapman.finalproject;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.ViewHolder>{
    private final String LOGTAG = "ListAdapter";
    protected Context context;

    @Override
    //inflates the view
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.d(LOGTAG, "onCreateViewHolder()");

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        this.context = parent.getContext();
        View v = inflater.inflate(R.layout.cell_note, parent, false);

        return new ViewHolder(v);
    }

    @Override
    //sets up the holder
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Log.d(LOGTAG, "onBindViewHolder(" + position + ")");
        // Get list element at specified position
        NoteModel note = NoteCollection.GetInstance(context).getListElements().get(position);

        // Setup the ViewHolder
        holder.setup(note);
    }

    @Override
    public int getItemCount() {
        int count =  NoteCollection.GetInstance(this.context).getListElements().size();
        Log.d(LOGTAG, "getItemCount() is now returning " + count);
        return count;
    }

    //removes item from collection
    void remove(int position) {
        Log.d(LOGTAG, "getItemCount()");
        NoteCollection.GetInstance(context).remove(position);
        notifyItemRemoved(position);
    }

    //swap 2 elements in collection
    void swap(int firstPosition, int secondPosition) {
        NoteCollection.GetInstance(context).swap(firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private NoteModel note;

        private TextView titleTextView;

        //constructor
        ViewHolder(View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(this);

            this.titleTextView = itemView.findViewById(R.id.tv_title);
        }

        //sets text and stuff
        void setup(NoteModel note)
        {
            this.note = note;

            this.titleTextView.setText(note.getTitle());
        }

        @Override
        //the on click listener, passes intent to activity
        public void onClick(View view)
        {
            Intent listElementIntent = new Intent(view.getContext(), NoteDescriptionActivity.class);
            listElementIntent.putExtra(NoteDescriptionActivity.EXTRA_NOTE_ID, this.note.getId());

            view.getContext().startActivity(listElementIntent);
        }
    }
}

