package com.imam.myasi;

import android.provider.BaseColumns;


public final class QuizContract {

    private QuizContract() {
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_CATEGORY = "category";
    }

    public static class UserTable implements BaseColumns{
        public static final String TABLE_NAME = "user_asi";
        public static final String COLUMN_NAMA = "nama";
        public static final String COLUMN_KTP = "ktp";
        public static final String COLUMN_HP = "hp";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
    }

    public static class DiagnosaTable implements BaseColumns {
        public static final String TABLE_NAME = "diagnosa";
        public static final String COLUMN_JUDUL = "judul";
        public static final String COLUMN_TANGGAL = "tanggal";
    }
}
