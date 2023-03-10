/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.projects.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/* Handles operations on projects LiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialProjectList = projectList(resources)
    private val projectsLiveData = MutableLiveData(initialProjectList)

    /* Adds project to liveData and posts value. */
    fun addProject(project: Project) {
        val currentList = projectsLiveData.value
        if (currentList == null) {
            projectsLiveData.postValue(listOf(project))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, project)
            projectsLiveData.postValue(updatedList)
        }
    }

    /* Returns project given an ID. */
    fun getProjectForId(id: Long): Project? {
        projectsLiveData.value?.let { projects->
            return projects.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getProjectList(): LiveData<List<Project>> {
        return projectsLiveData
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}