package com.sshartika.punya_atik.androidasar6offlinedatabase;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class RealmHelper {
    private Context context;
    private Realm realm;

    public RealmHelper(Context context){
        this.context = context;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    //insert
    public void insertData (CtatanModel catatan){
        realm.beginTransaction();
        realm.copyToRealm(catatan);
        realm.commitTransaction();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Toast.makeText(context,"Data Berhasil di Tambahkan", Toast.LENGTH_SHORT).show();
            }
        });
        realm.close();

    }
    //next id
    public long getNextId(){
        if (realm.where(CtatanModel.class).count() != 0){
            long id = realm.where(CtatanModel.class).max("id").longValue();
            return id +1;
        }else {
            return 1;
        }
    }
    //menampilkan data
    public List<CtatanModel> showData(){
       // RealmResults<CtatanModel> datahasil = realm.where(CtatanModel.class).findAll();
        //List<CtatanModel> datalist = new ArrayList<>();
        //datalist.addAll(realm.copyFromRealm(datahasil));
        //realm.commitTransaction();
        //return datalist;
        return realm.where(CtatanModel.class).findAll();
    }

    //menampilkan satu data
    public CtatanModel showOneData(Integer id){
        CtatanModel data = realm.where(CtatanModel.class).equalTo("id", id).findFirst();
        return data;
    }

    //Update
    public void updateData(CtatanModel catatan){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(catatan);
        realm.commitTransaction();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Toast.makeText(context, "Data berhasil di Update", Toast.LENGTH_SHORT).show();

            }
        });
        realm.close();
    }
    //Delete
    public void deleteData(Integer id){
        realm.beginTransaction();
        CtatanModel catatan = realm. where(CtatanModel.class).equalTo("id", id).findFirst();
        catatan.deleteFromRealm();
        realm.commitTransaction();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Toast.makeText(context, "Data berhasil di Hapus", Toast.LENGTH_SHORT).show();
            }
        });
        realm.close();
    }



}
