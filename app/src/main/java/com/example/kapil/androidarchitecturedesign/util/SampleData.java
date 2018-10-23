package com.example.kapil.androidarchitecturedesign.util;

import com.example.kapil.androidarchitecturedesign.database.NoteEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {


    public static final String SAMPLE_TEXT_1 = "Out in the straits, ships sail on in grey smoke:";
    public static final String SAMPLE_TEXT_2 =" On a full moon\n laughing together";
    public static final String SAMPLE_TEXT_3 ="When ready we talked according to the mood of the sea: slow some days, others with stories taller then a mast. All the time I ignored the paintings and drawings; some of sailors weeping, others of ships, some with oars, some with sails, breaking apart in wild waves, and others of women like bleached bones on the beach looking out to sea. Some were by him but most were by the others who had rested here. Once they told my future but it was my past that betrayed. A husband dead, a sister dust - both mere words - when once one a chesty laugh and the smell of wood smoke and the other a giggle over secrets and gossip. And I had no child to comfort me. I prayed to the Gods, bled the Bull, and threw doves to the wind. Then on the day of storms, the sea gave me a son, his skin water soft and his breath of mist. I asked not the price.\n\n" + "Then when my skin grew to wood bark, my teeth fall as autumn seeds and breasts became bloated baskets, the sea came for me. And my grandchildren found only the sea-spray of a hot summer’s day. Since then, many have seen my eyes and felt my embrace and learnt that no lamp window waits. Yet I keep some safe for women wearing the water of a child.";

    private static Date getDate(int diff){
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MILLISECOND, diff);
        return cal.getTime();

    }

    public static List<NoteEntity> getNotes(){

        List<NoteEntity> notes = new ArrayList<>();

        notes.add(new NoteEntity(1,getDate(0),SAMPLE_TEXT_1));
        notes.add(new NoteEntity(2,getDate(-1),SAMPLE_TEXT_2));
        notes.add(new NoteEntity(3,getDate(-2),SAMPLE_TEXT_3));

        return notes;

    }


    public static List<NoteEntity> getNotesDusra(int size){

        List<NoteEntity> notes = new ArrayList<>();

        notes.add(new NoteEntity(size+1,getDate(0),SAMPLE_TEXT_1));
        notes.add(new NoteEntity(size+2,getDate(-1),SAMPLE_TEXT_2));
        notes.add(new NoteEntity(size+3,getDate(-2),SAMPLE_TEXT_3));

        return notes;

    }
}
