package edu.chapman.finalproject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class NoteCollection {
    private static NoteCollection collection;

    static NoteCollection GetInstance()
    {
        //singleton, makes sure there's only ever one instance of the collection
        if (collection == null)
        {
            collection = new NoteCollection();
        }

        return collection;
    }

    private List<NoteModel> notes;

    private NoteCollection()
    {
        //File files = new File(); TODO
    }

    //return the junk from the collection without returning the actual collection
    List<NoteModel> getListElements()
    {
        return this.notes;
    }

    //get a single element from the list
    NoteModel getListElement(String id)
    {
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
