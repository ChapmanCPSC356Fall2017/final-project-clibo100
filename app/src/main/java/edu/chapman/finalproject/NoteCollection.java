package edu.chapman.finalproject;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class NoteCollection {
    private static final String TAG = "NoteCollection";
    private static NoteCollection collection;
    protected Context context;

    static NoteCollection GetInstance(Context context)
    {
        Log.d(TAG, "GetInstance()");
        //singleton, makes sure there's only ever one instance of the collection
        if (collection == null && context != null)
        {
            collection = new NoteCollection(context);
        }

        return collection;
    }

    private List<NoteModel> notes;

    private NoteCollection(Context context) {
        Log.d(TAG, "NoteCollection()");
        this.context = context;
        this.notes = new ArrayList<>();
        Log.d(TAG, "The size of the array that should contain bozo.txt is" + notes.size());
        File[] files = context.getFilesDir().listFiles();
        for (File file : files)
        {
            NoteModel note = new NoteModel();
            note.setTitle(file.getName());
            StringBuilder text = new StringBuilder();
            BufferedReader br = null;
            try {

                br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
                note.setBody(text.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            notes.add(note);

        }
    }

    //return the junk from the collection without returning the actual collection
    List<NoteModel> getListElements()
    {
        Log.d(TAG, "notecollection getlistelements");
        return this.notes;
    }

    //get a single element from the list
    NoteModel getListElement(String id)
    {
        Log.d(TAG, "notecollection getlistelement");
        for(NoteModel note : this.notes)
        {
            if (note.getId().equals(id))
            {
                return note;
            }
        }

        return null;
    }

    //remove an element from the list
    void remove(int position)
    {
        this.notes.remove(position);
    }

    //swap 2 elements in a list
    void swap(int firstPosition, int secondPosition)
    {
        Collections.swap(this.notes, firstPosition, secondPosition);
    }

}
