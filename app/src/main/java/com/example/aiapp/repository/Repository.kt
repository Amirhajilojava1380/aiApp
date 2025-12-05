package com.example.aiapp.repository

import com.example.aiapp.data.remotedatasource.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource : RemoteDataSource,
) {
    val remote = remoteDataSource
}