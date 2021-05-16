package com.example.proyandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {

    private OpenHelper oh;
    private Context c;

    public ViewModel(Context c)
    {
        oh =new OpenHelper(c,"DBJuntas",null,1);

    }

    public void crearJunta(int nPersonas,int money,String fecha)
    {
        int idVal = countJuntas()+1;
        int idSorteo = countSorteo()+1;
        SQLiteDatabase db = oh.getWritableDatabase();
        db.execSQL("INSERT INTO Juntas (id,nPersonas,idSorteo,cantidad,fecha) VALUES (?,?,?,?,?)",new String [] {String.valueOf(idVal),String.valueOf(nPersonas),String.valueOf(idSorteo),String.valueOf(money),fecha});
        db.execSQL("INSERT INTO PARTICIPANTES(id,nombre,idJunta,pagado) VALUES (1,'Gladys',?,0)",new String [] {String.valueOf(idVal)});
        db.execSQL("INSERT INTO SORTEO(id,nombre,cobrado) VALUES (1,'Gladys',0)");
        db.close();

    }
    public void pagarByParticipante(int id)
    {
        SQLiteDatabase db = oh.getWritableDatabase();
        db.execSQL("UPDATE PARTICIPANTES SET pagado=1 WHERE id=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void pagarJunta(int id)
    {
        SQLiteDatabase db = oh.getWritableDatabase();
        db.execSQL("UPDATE SORTEO SET cobrado=1 WHERE id=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void quitarPagoJunta(int id)
    {
        SQLiteDatabase db = oh.getWritableDatabase();
        db.execSQL("UPDATE SORTEO SET cobrado=0 WHERE id=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public boolean isCobrado(int id)
    {
        SQLiteDatabase db = oh.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT Cobrado FROM SORTEO WHERE id=?",new String[]{String.valueOf(id)});
        c.moveToFirst();

        if(c.getInt(0)== 1)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public int totalPagados()
    {
        SQLiteDatabase db = oh.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM PARTICIPANTES WHERE pagado=1",null);
        int total = c.getCount();
        db.close();

        return total;
    }
    public void actualizarSorteo(List <String> dataSet)
    {
        SQLiteDatabase db = oh.getWritableDatabase();
        try {
            for (int i = 0; i < dataSet.size(); i++)
            {
                db.execSQL("UPDATE SORTEO SET nombre=? WHERE id=?", new String[]{dataSet.get(i),String.valueOf(i+1)});
            }
        }
        catch (Exception e)
        {
            Log.i("El error", e.toString());
        }
        finally {
            db.close();
        }

    }

    public void reiniciarPagos()
    {
        SQLiteDatabase db = oh.getWritableDatabase();
        db.execSQL("UPDATE PARTICIPANTES SET pagado=0 WHERE pagado=1");

    }

    public void crearParticipante(String nombre)
    {
        int idVal = countParticipantes()+1;
        SQLiteDatabase db = oh.getWritableDatabase();
        db.execSQL("INSERT INTO PARTICIPANTES (id,nombre,idJunta,pagado) VALUES (?,?,?,0)",new String [] {String.valueOf(idVal),nombre,String.valueOf(countJuntas())});
        db.close();
    }

    public void crearSorteo()
    {
        Sorteo s = new Sorteo(countParticipantes());
        SQLiteDatabase db = oh.getWritableDatabase();
        for(int i = 1;i<s.getSort().length;i++)
        {
            Cursor c = db.rawQuery("Select nombre FROM Participantes Where id=?",new String [] { String.valueOf(s.getSort()[i])});
            c.moveToFirst();
            db.execSQL("INSERT INTO SORTEO(id,nombre,cobrado) VALUES (?,?,0)", new String[]{String.valueOf(countSorteo()+1),c.getString(0)});
        }
        db.close();
    }

    public int countParticipantes() {

        SQLiteDatabase db = oh.getReadableDatabase();
        int countParticipante = 0;
        try
        {
            Cursor c = db.rawQuery("SELECT * FROM PARTICIPANTES",null);
            countParticipante = c.getCount();
        }
        catch (Exception e)
        {
            Log.i("El error", e.toString());
        }
        return countParticipante;
    }

    public int countJuntas() {

        SQLiteDatabase db = oh.getReadableDatabase();
        int countCodigo = 0;
        try
        {
            Cursor c = db.rawQuery("SELECT * FROM JUNTAS",null);
            countCodigo = c.getCount();
        }
        catch (Exception e)
        {
            Log.i("El error", e.toString());
        }
        return countCodigo;
    }

    public int countSorteo() {

        SQLiteDatabase db = oh.getReadableDatabase();
        int countSorteo = 0;
        try
        {
            Cursor c = db.rawQuery("SELECT * FROM SORTEO",null);
            countSorteo = c.getCount();
        }
        catch (Exception e)
        {
            Log.i("El error", e.toString());
        }
        return countSorteo;
    }

    public ArrayList<Participante> getParticipantes(){

        ArrayList <Participante> participantes = new ArrayList<Participante>();
        SQLiteDatabase db = oh.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT nombre,pagado FROM PARTICIPANTES",null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            Participante p = new Participante(c.getString(0),c.getInt(1));
            participantes.add(p);
        }

        return participantes;
    }

    public ArrayList<Participante> getParticipantesSorteo(){

        ArrayList <Participante> participantes = new ArrayList<Participante>();
        SQLiteDatabase db = oh.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT nombre,cobrado FROM SORTEO",null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            Participante p = new Participante(c.getString(0),c.getInt(1));
            participantes.add(p);
        }

        return participantes;
    }
    public String [] nombresSorteo(){

        String [] nombres = new String[countSorteo()];
        int i=0;
        SQLiteDatabase db = oh.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT nombre FROM SORTEO",null);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            nombres[i] = c.getString(0);
            i++;
        }

        return nombres;
    }

    public int getParticipantesJunta()
    {
        SQLiteDatabase db = oh.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT nPersonas FROM JUNTAS",null);
        c.moveToFirst();
        int n = c.getInt(0);

        return n;
    }
    public int getCantidad()
    {
        SQLiteDatabase db = oh.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT cantidad FROM JUNTAS",null);
        c.moveToFirst();
        int n = c.getInt(0);

        return n;
    }
}
