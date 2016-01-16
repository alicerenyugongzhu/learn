package com.example.learn;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DatabaseContext extends ContextWrapper {
/**
* ���캯��
* @param    testDatabaseSource �����Ļ���
*/
    public DatabaseContext(Context testDatabaseSource){
        super(testDatabaseSource);
    }
/**
* ������ݿ�·������������ڣ��򴴽��������
* @param    name
* @param    mode
* @param    factory
*/
    @Override
    public File getDatabasePath(String name) {
        //�ж��Ƿ����sd��
        boolean sdExist = android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment.getExternalStorageState());
        if(!sdExist){//���������,
            Log.e("alice", "No SD card");
            return null;
        }
        else{//�������
            //��ȡsd��·��
            String dbDir=android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
            dbDir += "/database";//���ݿ�����Ŀ¼
            String dbPath = dbDir+"/"+name;//���ݿ�·��
            //�ж�Ŀ¼�Ƿ���ڣ��������򴴽���Ŀ¼
            File dirFile = new File(dbDir);
            if(!dirFile.exists()){
            	Log.d("alice", "no database dir available");
                dirFile.mkdirs();
            }
            //���ݿ��ļ��Ƿ񴴽��ɹ�
            boolean isFileCreateSuccess = false;
            //�ж��ļ��Ƿ���ڣ��������򴴽����ļ�
            File dbFile = new File(dbPath);
            if(!dbFile.exists()){
            	Log.d("alice","no database path available");
                try {
                    isFileCreateSuccess = dbFile.createNewFile();//�����ļ�
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else
                isFileCreateSuccess = true;
            //�������ݿ��ļ�����
            if(isFileCreateSuccess)
                return dbFile;
            else
                return null;
        }
    }
/**
* ���������������������SD���ϵ����ݿ�ģ�android 2.3�����»�������������
*
* @param    name
* @param    mode
* @param    factory
*/
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                               SQLiteDatabase.CursorFactory factory) {
    	Log.d("alice", "I am in the Database Context, I plan to setup database in SD Card");
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
        return result;
    }
/**
* Android 4.0����ô˷�����ȡ���ݿ⡣
*
* @see android.content.ContextWrapper#openOrCreateDatabase(java.lang.String, int,
*              android.database.sqlite.SQLiteDatabase.CursorFactory,
*              android.database.DatabaseErrorHandler)
* @param    name
* @param    mode
* @param    factory
* @param     errorHandler
*/
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory,
                                 DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
        return result;
    }
}
