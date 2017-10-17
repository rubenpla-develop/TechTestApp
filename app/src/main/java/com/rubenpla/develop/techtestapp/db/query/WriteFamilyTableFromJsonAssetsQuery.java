package com.rubenpla.develop.techtestapp.db.query;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.rubenpla.develop.techtestapp.db.DataStore;
import com.rubenpla.develop.techtestapp.db.DataStoreTables;
import com.rubenpla.develop.techtestapp.mvp.model.Family;
import com.rubenpla.develop.techtestapp.mvp.model.FamilyMember;

import java.util.ArrayList;

public class WriteFamilyTableFromJsonAssetsQuery implements DataStore.DatabaseWritableQuery {

    @Override
    public Object execute(SQLiteDatabase db, String tableName, Object object) throws Exception {
        DataStoreTables dataStoreTables = new DataStoreTables();
        Family family = (Family) object;
        ArrayList<ContentValues> list = new ArrayList<>();
        

        for (FamilyMember member : family.getFamilyList()) {
            ContentValues values = new ContentValues(); 
            

            values.put(dataStoreTables.TABLE_FAMILY_FIELD_NAME, member.getName());
            values.put(dataStoreTables.TABLE_FAMILY_FIELD_LASTNAME, member.getLastName());
            values.put(dataStoreTables.TABLE_FAMILY_FIELD_AGE, member.getAge());
            values.put(dataStoreTables.TABLE_FAMILY_FIELD_FAMILY_ROLE, member.getFamilyRole());

            db.insert(tableName,null, values);
        }

        db.close();

        return null;
    }
}
