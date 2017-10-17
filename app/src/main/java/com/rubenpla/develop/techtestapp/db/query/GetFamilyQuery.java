package com.rubenpla.develop.techtestapp.db.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rubenpla.develop.techtestapp.db.DataStore;
import com.rubenpla.develop.techtestapp.mvp.model.FamilyMember;

import java.util.LinkedList;
import java.util.List;

public class GetFamilyQuery implements DataStore.DatabaseReadableQuery {

    private final int COLUMN_ID           = 0;
    private final int COLUMN_NAME         = 1;
    private final int COLUMN_LAST_NAME    = 2;
    private final int COLUMN_AGE          = 3;
    private final int COLUMN_FAMILY_ROLE  = 4;
    
    @Override
    public Object execute(SQLiteDatabase db, final String tableName) throws Exception {
        List<FamilyMember> members = new LinkedList<>();
        String query = "SELECT  * FROM " + tableName;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                FamilyMember member = new FamilyMember();
                member.setName(cursor.getString(COLUMN_NAME));
                member.setLastName(cursor.getString(COLUMN_LAST_NAME));
                member.setAge(Integer.parseInt(cursor.getString(COLUMN_AGE)));
                member.setFamilyRole(cursor.getString(COLUMN_FAMILY_ROLE));
                members.add(member);
            } while (cursor.moveToNext());
        }

        return members;
    }
}
