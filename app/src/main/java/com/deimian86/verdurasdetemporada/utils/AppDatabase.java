package com.deimian86.verdurasdetemporada.utils;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.annotation.NonNull;

import com.deimian86.verdurasdetemporada.entities.Fruta;
import com.deimian86.verdurasdetemporada.entities.FrutaDao;
import com.deimian86.verdurasdetemporada.entities.FrutaMes;
import com.deimian86.verdurasdetemporada.entities.FrutaMesDao;
import com.deimian86.verdurasdetemporada.entities.Verdura;
import com.deimian86.verdurasdetemporada.entities.VerduraDao;
import com.deimian86.verdurasdetemporada.entities.VerduraMes;
import com.deimian86.verdurasdetemporada.entities.VerduraMesDao;

@Database(entities = {Verdura.class, VerduraMes.class, Fruta.class, FrutaMes.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract VerduraDao verduraDao();
    public abstract VerduraMesDao verduraMesDao();
    public abstract FrutaDao frutaDao();
    public abstract FrutaMesDao frutaMesDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    RoomDatabase.Callback rdc = getCallBackInstance(context);
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"verduras-frutas-db").addCallback(rdc).build();
                }
            }
        }
        return INSTANCE;
    }

    // Crear o abrir base de datos
    private static RoomDatabase.Callback getCallBackInstance(final Context context) {
        return new RoomDatabase.Callback(){
            public void onCreate (@NonNull SupportSQLiteDatabase database){
                // Llenamos la base de datos solo tras crearla
                AppDatabase_Create_Async createDb = new AppDatabase_Create_Async(INSTANCE, context);
                createDb.execute();
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
                BusProvider.getInstance().post("ONOPEN");
            }
        };
    }

}
