package com.example.shadipoc

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.shadipoc.di.AppComponent
import com.example.shadipoc.di.DaggerAppComponent
import com.example.shadipoc.di.modules.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class DaggerApp : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingAndroidFragmentInjector: DispatchingAndroidInjector<Fragment>

    /** Returns an [AndroidInjector] of [Activity]s.  */
    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidFragmentInjector
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
            .inject(this)
    }

    fun getApplicationComponent(): AppComponent {
        return appComponent
    }
}