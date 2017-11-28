package edu.chapman.finalproject;

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
        this.notes = new ArrayList<>();
        //populate collection with objectively the best games ever made don't fight me on this
        NoteModel element1 = new NoteModel();
        element1.setTitle("Harvest Moon: Magical Melody");
        element1.setBody("This is a social simulation video game for the GameCube developed by Marvelous Interactive." +
                "The game has two main objectives: to wake the Harvest Goddess and to marry one of the townspeople. " +
                "The Harvest Goddess has turned herself to stone out of melancholy at the townspeople's disregard. " +
                "The player must gather musical notes by finishing various chores and milestones. ");
        this.notes.add(element1);

        NoteModel element2 = new NoteModel();
        element2.setTitle("LoZ: Wind Waker");
        element2.setBody("The game is set on a group of islands in a vast sea, a departure for the series. " +
                "The player controls series protagonist Link as he attempts to save his sister from the sorcerer Ganon and becomes embroiled " +
                "in a struggle for the Triforce, a sacred relic that grants its holder's wishes.");
        this.notes.add(element2);

        NoteModel element3 = new NoteModel();
        element3.setTitle("Stardew Valley");
        element3.setBody("Stardew Valley is an indie farming simulation role-playing video game developed by Eric " +
                "\"ConcernedApe\" Barone and published by Chucklefish. The game was released for Microsoft Windows in February 2016, " +
                "with ports for OS X, Linux, PlayStation 4 and Xbox One released later that year. " +
                "A Nintendo Switch port was released in October 2017.");
        this.notes.add(element3);

        NoteModel element4 = new NoteModel();
        element4.setTitle("Super Mario Galaxy");
        element4.setBody("The levels in the game consist of galaxies filled with minor planets and worlds, with different " +
                "variations of gravity, the central element of gameplay. The concept for the game's use of spherical platforms was " +
                "first conceptualised from ideas used in Super Mario 128, a technology demonstration shown at Nintendo Space World in 2000. ");
        this.notes.add(element4);

        NoteModel element5 = new NoteModel();
        element5.setTitle("LoZ: Breath of the Wild");
        element5.setBody("Gameplay of Breath of the Wild departs from most games in The Legend of Zelda series because it " +
                "features a fully open-world environment, twelve times larger than the overworld in Twilight Princess, with less emphasis " +
                "on defined entrances and exits to areas. Similar to the original The Legend of Zelda, the player is placed into the game's world " +
                "with very little instruction, and is allowed to explore freely at their own pace.");
        this.notes.add(element5);

        NoteModel element6 = new NoteModel();
        element6.setTitle("Bioshock Infinite");
        element6.setBody("BioShock Infinite is set in 1912 and takes place in a fictional steampunk city-state called \"Columbia\"—named in " +
                "homage to the female personification of the United States—which is suspended in the air through a combination of giant blimps," +
                " balloons, reactors, propellers, and \"quantum levitation.\" The city of Columbia was founded by self-proclaimed prophet " +
                "Zachary Hale Comstock, who used his connections in Congress to have the American government build it");
        this.notes.add(element6);

        NoteModel element7 = new NoteModel();
        element7.setTitle("Life is Strange");
        element7.setBody("Life Is Strange is a graphic adventure played from a third-person view. The mechanic of rewinding time allows " +
                "the player to redo any action that has been taken. The player can examine and interact with objects, which enables puzzle " +
                "solving in the form of fetch quests and making changes to the environment. Items that are collected before time travelling " +
                "will be kept in the inventory after the fact.");
        this.notes.add(element7);

        NoteModel element8 = new NoteModel();
        element8.setTitle("Animal Crossing: New Leaf");
        element8.setBody("Players take control of a villager who is moving into a new town. Upon arrival, however, the player is mistaken " +
                "as the town mayor and is given that position instead of being a mere resident. Like previous games in the series, the game revolves " +
                "around the player as they explore their town, talk with other residents, and participate in various activities such as fishing and " +
                "bug catching. Doing various activities or selling various items earns Bells which players can use to purchase various items such as " +
                "furniture or clothes, or pay towards loans used to renovate their house. ");
        this.notes.add(element8);

        NoteModel element9 = new NoteModel();
        element9.setTitle("Pokemon Sun/Mooon");
        element9.setBody("As with previous installments, each game follows the journey of a young Pokémon trainer as they train Pokémon. " +
                "This time, the game takes place in the Alola region—based on Hawaii—with the object of the game being to thwart the schemes of " +
                "Team Skull, and later the Aether Foundation, all while attempting to challenge various Pokémon trainers of gradually increasing difficulty. ");
        this.notes.add(element9);

        NoteModel element10 = new NoteModel();
        element10.setTitle("Donkey Kong Country");
        element10.setBody("Donkey Kong Country is a platform game where players must complete 40 different side-scrolling levels (or 41 in the" +
                " Game Boy Color version) and recover the Kongs' banana hoard, which has been stolen by the Kremlings. Each level is uniquely themed " +
                "and consists of varying tasks such as swimming, riding in mine carts, launching out of barrel cannons, or swinging from vine to vine.");
        this.notes.add(element10);
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
