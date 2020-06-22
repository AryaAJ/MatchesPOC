package com.example.shadipoc.repository

import android.annotation.SuppressLint
import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shadipoc.api.UserApi
import com.example.shadipoc.data.Result
import com.example.shadipoc.data.room.UserData
import com.example.shadipoc.room.dao.UserDao
import com.example.shadipoc.room.db.UserRoomDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class UserRepository @Inject constructor(application: Application) {

    private val db = UserRoomDatabase.getDatabase(application)
    private var userDao: UserDao = db.userDao()
    private var userList = MutableLiveData<List<UserData>>()

    @Inject
    lateinit var application: Application

    private class ListInsertAsyncTask internal constructor(private val asyncTaskDao: UserDao) :
        AsyncTask<List<UserData>, Void, Void>() {

        override fun doInBackground(vararg params: List<UserData>): Void? {
            asyncTaskDao.insertUsers(params[0])
            return null
        }
    }

    private class InsertAsyncTask internal constructor(private val asyncTaskDao: UserDao) :
        AsyncTask<UserData, Void, Void>() {

        override fun doInBackground(vararg params: UserData): Void? {
            asyncTaskDao.insertUser(params[0])
            return null
        }
    }

    private class UpdateAsyncTask internal constructor(private val asyncTaskDao: UserDao) :
        AsyncTask<UserData, Void, Void>() {

        override fun doInBackground(vararg params: UserData): Void? {
            asyncTaskDao.updateUser(params[0])
            return null
        }
    }

    fun insertUsers(newproduct: List<UserData>) {
        val task = ListInsertAsyncTask(userDao)
        task.execute(newproduct)
    }

    fun insertUser(newproduct: UserData) {
        val task = InsertAsyncTask(userDao)
        task.execute(newproduct)
    }

    fun updateUser(newproduct: UserData) {
        val task = UpdateAsyncTask(userDao)
        task.execute(newproduct)
    }

    fun getAllUsers(): LiveData<List<UserData>> {
        return userDao.getAllUsers()
    }

    @SuppressLint("CheckResult")
    fun fetchOnlineData(): MutableLiveData<List<UserData>> {

        employeeApi.getUserList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    insertUsers(prepareData(it.results))
                }, {
                    Log.d("online ", "error :" + it.message)
                }
            )

        return userList
    }

    private fun prepareData(results: List<Result>): List<UserData> {
        //convert to custom format
        var userDataList = ArrayList<UserData>()
        for (i in results) {
            userDataList.add(
                UserData(
                    i.cell,
                    i.dob.date,
                    i.dob.age,
                    i.email,
                    i.gender,
                    i.location.city + ", " + i.location.state,
                    i.name.first,
                    i.name.last,
                    i.nat,
                    i.phone,
                    i.picture.medium,
                    i.picture.large,
                    0
                )
            )
        }
        return userDataList
    }
}
