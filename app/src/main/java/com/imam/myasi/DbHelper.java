package com.imam.myasi;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.imam.myasi.QuizContract.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "MyAsi.db";
        private static final int DATABASE_VERSION = 1;

        private SQLiteDatabase db;

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            this.db = db;

            final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                    QuestionsTable.TABLE_NAME + " ( " +
                    QuestionsTable._ID + " VARCHAR(10) PRIMARY KEY," +
                    QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                    QuestionsTable.COLUMN_CATEGORY + " VARCHAR(20)" +
                    ")";

            final String SQL_CREATE_USER_TABLE = "CREATE TABLE " +
                    UserTable.TABLE_NAME + " ( " +
                    UserTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    UserTable.COLUMN_NAMA + " VARCHAR(30), " +
                    UserTable.COLUMN_HP + " VARCHAR(15), " +
                    UserTable.COLUMN_KTP + " VARCHAR(20), " +
                    UserTable.COLUMN_EMAIL + " VARCHAR(30), " +
                    UserTable.COLUMN_PASSWORD + " VARCHAR(50) " +
                    ")";

            final String SQL_CREATE_DIAGNOSA_TABLE = "CREATE TABLE " +
                    DiagnosaTable.TABLE_NAME + " ( " +
                    DiagnosaTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DiagnosaTable.COLUMN_JUDUL + " VARCHAR(50), " +
                    DiagnosaTable.COLUMN_TANGGAL + " DATE, " +
                    DiagnosaTable.COLUMN_STATUS +" VARCHAR(1) "+
                    ")";

            final String SQL_CREATE_HASIL_TABLE = "CREATE TABLE " +
                    HasilTable.TABLE_NAME + " ( " +
                    HasilTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    HasilTable.ID_DIAGNOSA + " INTEGER, " +
                    HasilTable.ID_QUESTION + " VARCHAR(10), " +
                    HasilTable.COLUMN_HASIL + " INTEGER "+
                    ")";

            db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
            db.execSQL(SQL_CREATE_USER_TABLE);
            db.execSQL(SQL_CREATE_DIAGNOSA_TABLE);
            db.execSQL(SQL_CREATE_HASIL_TABLE);

            fillQuestionsTable();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + DiagnosaTable.TABLE_NAME);
            onCreate(db);
        }

        private void fillQuestionsTable() {
            Question q1 = new Question("Payudara tegang karena terisi ASI" , "ibu", "ibu1");
            addQuestion(q1);
            Question q2 = new Question("Ibu relax", "ibu", "ibu2");
            addQuestion(q2);
            Question q3 = new Question("Let down reflex baik", "ibu","ibu3");
            addQuestion(q3);
            Question q4 = new Question("Ibu menggunakan payudara bergantian", "ibu","ibu4");
            addQuestion(q4);
            Question q5 = new Question("Posisi perlekatan benar, putting tidak lecet", "ibu", "ibu5");
            addQuestion(q5);
            Question q6 = new Question("Ibu menyusui bayi tanpa jadwal, minimal 2 jam sekali", "ibu", "ibu6");
            addQuestion(q6);
            Question q7 = new Question("Ibu terlihat memerah payudara karena payudara penuh", "ibu", "ibu7");
            addQuestion(q7);
            Question q8 = new Question("Payudara kosong setelah bayi menyusu sampai kenyang dan tertidur", "ibu", "ibu8");
            addQuestion(q8);
            Question q9 = new Question("Ibu dapat memberikan ASI perah menggunakan cangkir atau sendok", "ibu", "ibu9");
            addQuestion(q9);
            Question q10 = new Question("BAK bayi minimal 6-8 x per hari", "bayi", "bayi1");
            addQuestion(q10);
            Question q11 = new Question("Urin jernih", "bayi", "bayi2");
            addQuestion(q11);
            Question q12 = new Question("Bayi tenang, tidur nyenyak 2-3 jam", "bayi", "bayi3");
            addQuestion(q12);
            Question q13 = new Question("BAB 2-5 per hari", "bayi", "ibu4");
            addQuestion(q13);
            Question q14 = new Question("BAB berwarna keemas an atau hijau kehitaman", "bayi", "bayi5");
            addQuestion(q14);
            Question q15 = new Question("Frekuensi menyusui >8x per hari", "bayi", "bayi6");
            addQuestion(q15);
            Question q16 = new Question("Saya merasa lebih gugup dan cemas dari biasanya" , "cemas", "cemas1");
            addQuestion(q16);
            Question q17 = new Question("Saya merasa takut tanpa alasan sama sekali", "cemas", "cemas2");
            addQuestion(q17);
            Question q18 = new Question("Saya mudah marah atau merasa panic", "cemas","cemas3");
            addQuestion(q18);
            Question q19 = new Question("Saya merasa seperti jatuh terpisah dan akan hancur berkeping-keping", "cemas","cemas4");
            addQuestion(q19);
            Question q20 = new Question("Saya merasa bahwa semuanya baik-baik saja dan tidak ada hal buruk akan terjadi", "cemas", "cemas5");
            addQuestion(q20);
            Question q21 = new Question("Lengan dan kaki saya gemetaran", "cemas", "cemas6");
            addQuestion(q21);
            Question q22 = new Question("Saya terganggu oleh nyeri kepala leher dan nyeri pinggul", "cemas", "cemas7");
            addQuestion(q22);
            Question q23 = new Question("Saya merasa lemah dan mudah lelah", "cemas", "cemas8");
            addQuestion(q23);
            Question q24 = new Question("Saya merasa tenang dan dapat duduk diam dengan mudah", "cemas", "cemas9");
            addQuestion(q24);
            Question q25 = new Question("Saya merasa jantung saya berdebar-debar", "cemas", "cemas10");
            addQuestion(q25);
            Question q26 = new Question("Saya merasa pusing tujuh keliling", "cemas", "cemas11");
            addQuestion(q26);
            Question q27 = new Question("Saya telah pingsan atau merasa seperti itu", "cemas", "cemas12");
            addQuestion(q27);
            Question q28 = new Question("Saya dapat bernapas dengan mudah", "cemas", "cemas13");
            addQuestion(q28);
            Question q29 = new Question("Saya merasa jari-jari tangan dan kaki mati rasa dan kesemutan", "cemas", "cemas14");
            addQuestion(q29);
            Question q30 = new Question("Saya terganggu oleh nyeri lambung atau gangguan pencernaan", "cemas", "cemas15");
            addQuestion(q30);

        }

        private void addQuestion(Question question) {
            ContentValues cv = new ContentValues();
            cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
            cv.put(QuestionsTable.COLUMN_CATEGORY, question.getCategory());
            cv.put(QuestionsTable._ID, question.getId());
            db.insert(QuestionsTable.TABLE_NAME, null, cv);
        }

        public void addUser(UserModel user){
            db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(UserTable.COLUMN_NAMA, user.getNama());
            cv.put(UserTable.COLUMN_KTP, user.getKtp());
            cv.put(UserTable.COLUMN_HP, user.getHp());
            cv.put(UserTable.COLUMN_EMAIL, user.getEmail());
            cv.put(UserTable.COLUMN_PASSWORD, user.getPassword());
            Log.d(TAG, "addUser: "+cv);
            db.insert(UserTable.TABLE_NAME, null, cv);
        }

        public void addDiagnosa(DiagnosaModel diagnosa){
            Date date = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DiagnosaTable.COLUMN_JUDUL, diagnosa.getJudul());
            cv.put(DiagnosaTable.COLUMN_TANGGAL, ft.format(date));
            cv.put(DiagnosaTable.COLUMN_STATUS, "0");
            db.insert(DiagnosaTable.TABLE_NAME, null, cv);
        }

        public void addHasil(HasilModel hasil){
            db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(HasilTable.ID_DIAGNOSA, hasil.getIddiagnosa());
            cv.put(HasilTable.ID_QUESTION, hasil.getIdquestion());
            cv.put(HasilTable.COLUMN_HASIL, hasil.getHasil());
            db.insert(HasilTable.TABLE_NAME, null, cv);
        }

        @SuppressLint("Range")
        public String findDiagnosa(DiagnosaModel dm){
            String status = "0";
            db = getReadableDatabase();
            Cursor c =  db.rawQuery("SELECT * FROM "+DiagnosaTable.TABLE_NAME+" WHERE "+
                    DiagnosaTable._ID + " = '"+dm.getId()+"' ",null);
            if(c.moveToFirst()){
                status = c.getString(c.getColumnIndex(DiagnosaTable.COLUMN_STATUS));
                c.close();
            }
            return status;

        }

        @SuppressLint("Range")
        public String findUser(UserModel user){
            String email;
            db = getReadableDatabase();
            Cursor c =  db.rawQuery("SELECT * FROM "+UserTable.TABLE_NAME+" WHERE "+
                            UserTable.COLUMN_EMAIL + " = '"+ user.getEmail()+"' AND "+
                            UserTable.COLUMN_PASSWORD + " = '"+ user.getPassword()+"' ",null);
            if(c.moveToFirst()){
                email = c.getString(c.getColumnIndex(UserTable.COLUMN_EMAIL));
                c.close();
                return email;
            }else{
                c.close();
                return "0";
            }


        }

        public Integer findHasil(HasilModel hasil){
            int count=0;
            String vvv=null;
            db = getReadableDatabase();
            Cursor c =  db.rawQuery("SELECT * FROM "+HasilTable.TABLE_NAME+" WHERE "+
                    HasilTable.ID_DIAGNOSA + " = '"+ hasil.getIddiagnosa()+"' AND "+
                    HasilTable.ID_QUESTION + " LIKE '%"+hasil.getKategori()+"%'",null);
            if (c.moveToNext()) {
                vvv="BG";
                do {
                    count = c.getInt(Integer.valueOf(c.getColumnIndex(HasilTable.COLUMN_HASIL)))+count;
                } while (c.moveToNext());
            }

            c.close();
            Log.d(TAG, "findHasil: hasilnya = "+hasil.getKategori());
            Log.d(TAG, "findHasil: COUNTnya = "+count);

            return count;
        }

        public String findHasil2(HasilModel hasil){
            db = getReadableDatabase();
            Cursor c =  db.rawQuery("SELECT * FROM "+HasilTable.TABLE_NAME+" WHERE "+
                    HasilTable.ID_DIAGNOSA + " = '"+ hasil.getIddiagnosa()+"' AND "+
                    HasilTable.ID_QUESTION + " = '"+hasil.getIdquestion()+"'",null);
            if(c.moveToFirst()){
                c.close();
                return "1";
            }else{
                c.close();
                return "0";
            }
        }

        public Integer findHasil3(HasilModel hasil){
            Integer cHasil=0;
            db = getReadableDatabase();
            Cursor c =  db.rawQuery("SELECT * FROM "+HasilTable.TABLE_NAME+" WHERE "+
                    HasilTable.ID_DIAGNOSA + " = '"+ hasil.getIddiagnosa()+"' AND "+
                    HasilTable.ID_QUESTION + " = '"+hasil.getIdquestion()+"'",null);
            if(c.moveToFirst()){
                cHasil = c.getInt(Integer.valueOf(c.getColumnIndex(HasilTable.COLUMN_HASIL)));
                c.close();
            }else{
                c.close();
            }
            return cHasil;
        }

        public void updateHasil(HasilModel hasil){
            db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(HasilTable.COLUMN_HASIL,hasil.getHasil());
            db.update(HasilTable.TABLE_NAME,cv,
                    HasilTable.ID_DIAGNOSA+"=? AND "+
                    HasilTable.ID_QUESTION+"=?",new String[]{hasil.getIddiagnosa(),hasil.getIdquestion()});

//            db.rawQuery("UPDATE "+HasilTable.TABLE_NAME+" SET hasil="+HasilTable.COLUMN_HASIL+
//                    " WHERE "+
//                    HasilTable.ID_DIAGNOSA + " = '"+ hasil.getIddiagnosa()+"' AND "+
//                    HasilTable.ID_QUESTION + " = '"+hasil.getIdquestion()+"'",null);
        }

        public void updateDiagnosa(DiagnosaModel dm){
            db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DiagnosaTable.COLUMN_STATUS,dm.getStatus());
            db.update(DiagnosaTable.TABLE_NAME,cv,DiagnosaTable._ID+"=?",new String[]{String.valueOf(dm.getId())});
        }


        @SuppressLint("Range")
        public List<Question> getAllQuestions() {
            List<Question> questionList = new ArrayList<>();
            db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

            if (c.moveToFirst()) {
                do {
                    Question question = new Question();
                    question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                    question.setCategory(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY)));
                    questionList.add(question);
                } while (c.moveToNext());
            }

            c.close();
            return questionList;
        }

        @SuppressLint("Range")
        public List<DiagnosaModel> getAllDiagnosa() {
            List<DiagnosaModel> dataList = new ArrayList<>();
            db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + DiagnosaTable.TABLE_NAME, null);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (c.moveToNext()) {
                do {
                    DiagnosaModel model = new DiagnosaModel();
                    model.setJudul(c.getString(c.getColumnIndex(DiagnosaTable.COLUMN_JUDUL)));
                    model.setTanggal(c.getString(c.getColumnIndex(DiagnosaTable.COLUMN_TANGGAL)));
                    model.setId(Integer.valueOf(c.getString(c.getColumnIndex(DiagnosaTable._ID))));
                    dataList.add(model);
                } while (c.moveToNext());
            }

            c.close();
            Log.d(TAG, "getAllDiagnosa: "+dataList);
            return dataList;
        }



        public void delData(String table, String Arg){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(table, "_id=?", new String[]{Arg});
            db.delete("hasildiagnosa", "iddiagnosa=?", new String[]{Arg});
        }

        public void delHasilData(String[] Arg){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("hasildiagnosa", "iddiagnosa='"+Arg[0]+"' AND idquestion LIKE '%"+
                    Arg[1]+"%'", null);
        }

        public Cursor viewData(String tableName, String where){
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM "+tableName+" WHERE category="+"'"+where+"'";
            Cursor cursor = db.rawQuery(query, null);

            return cursor;

        }

        public Cursor viewDataHasil(String[] where){
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM hasildiagnosa as hd JOIN quiz_questions as qq"+
                    " ON hd.idquestion=qq._id  WHERE hd.iddiagnosa = '"+where[0]
                    +"' AND qq.category="+"'"+where[1]+"'";
            Cursor cursor = db.rawQuery(query, null);

            return cursor;

        }

}
