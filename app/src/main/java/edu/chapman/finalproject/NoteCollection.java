package edu.chapman.finalproject;

import android.content.Context;
import android.util.Log;

import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class NoteCollection {
    private static final String TAG = "NoteCollection";
    private static NoteCollection collection;
    private Context context;

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
        File[] files = context.getFilesDir().listFiles();
        for (File file : files)
        {
            NoteModel note = new NoteModel();
            note.setTitle(file.getName());
            StringBuilder text = new StringBuilder();
            BufferedReader br;
            try {

                br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
                String firstFour = text.toString().substring(0, 4);
                Log.d(TAG, "this is firstFour " + firstFour);
                if (!Objects.equals(firstFour, "NULL"))
                {
                    note.setDate(DateTime.parse(text.toString().substring(0, 29)));
                    note.setBody(text.toString().substring(29));
                }
                else
                {
                    note.setBody(text.toString().substring(4));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            notes.add(note);

        }
    }

    //return the junk from the collection without returning the actual collection
    List<NoteModel> getListElements()
    {
        Log.d(TAG, "getListElements()");
        return this.notes;
    }

    //get a single element from the list
    NoteModel getListElement(String id)
    {
        Log.d(TAG, "getListElement()");
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
        Log.d(TAG, "remove(int)");
        File[] files = context.getFilesDir().listFiles();
        for (File file : files)
        {
            if (Objects.equals(file.getName(), this.notes.get(position).getTitle()))
            {
                Log.d(TAG, "deleted " + file.getName()+"file, congrats");
                boolean yolo = file.delete();
            }
            Log.d(TAG, "yo wtf");
        }
        this.notes.remove(position);
    }

    void remove(String title)
    {
        File[] files = context.getFilesDir().listFiles();
        for (File file : files)
        {
            if (Objects.equals(file.getName(), title))
            {
                boolean tolo = file.delete();
            }
        }
        int x = 0;
        while (x < notes.size())
        {
            if (Objects.equals(notes.get(x).getTitle(), title))
            {
                this.notes.remove(x);
            }
            x++;
        }
    }

    void add(NoteModel note)
    {
        Log.d(TAG, "add()");
        OutputStreamWriter outputStreamWriter;
        try {
            outputStreamWriter = new OutputStreamWriter(context.openFileOutput(note.getTitle(), Context.MODE_PRIVATE));
            Log.d(TAG, "this is what datetime looks like" + note.getDate());
            if (note.getDate() != null)
            {
                outputStreamWriter.write(note.getDate() + note.getBody());
            }
            else
            {
                outputStreamWriter.write("NULL" + note.getBody());
            }
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isMatch = false;
        for (NoteModel thisnote : this.notes)
        {
            if (Objects.equals(thisnote.getTitle(), note.getTitle()))
            {
                isMatch = true;
            }
        }
        if (!isMatch)
        {
            this.notes.add(note);
        }
    }

    //swap 2 elements in a list
    void swap(int firstPosition, int secondPosition)
    {
        Collections.swap(this.notes, firstPosition, secondPosition);
    }

}
