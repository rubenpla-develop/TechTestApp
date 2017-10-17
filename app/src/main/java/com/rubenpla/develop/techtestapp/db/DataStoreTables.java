package com.rubenpla.develop.techtestapp.db;

public class DataStoreTables {

    public final String ID = "id";

    //FAMILY TABLE

    public final String TABLE_FAMILY_MAIN = "Family";
    public final String TABLE_FAMILY_FIELD_NAME = "name";
    public final String TABLE_FAMILY_FIELD_LASTNAME = "lastName";
    public final String TABLE_FAMILY_FIELD_AGE = "age";
    public final String TABLE_FAMILY_FIELD_FAMILY_ROLE = "familyRole";

    public final String CREATE_FAMILY_TABLE_QUERY = "CREATE TABLE " + TABLE_FAMILY_MAIN + " ( "
            + ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_FAMILY_FIELD_NAME + " TEXT, "
            + TABLE_FAMILY_FIELD_LASTNAME + " TEXT, " + TABLE_FAMILY_FIELD_AGE + " INTEGER, "
            + TABLE_FAMILY_FIELD_FAMILY_ROLE + " TEXT )";

    // END OF FAMILY TABLE
}
