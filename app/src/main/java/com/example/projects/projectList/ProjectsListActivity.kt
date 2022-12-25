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

package com.example.projects.projectList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projects.projectDetail.ProjectDetailActivity
import com.example.projects.R
import com.example.projects.data.Project

const val PROJECT_ID = "project id"

class ProjectsListActivity : AppCompatActivity() {
    private val newProjectActivityRequestCode = 1
    private val projectsListViewModel by viewModels<ProjectsListViewModel> {
        ProjectsListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Instantiates headerAdapter and projectsAdapter. Both adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = HeaderAdapter()
        val projectsAdapter = ProjectsAdapter { project -> adapterOnClick(project) }
        val concatAdapter = ConcatAdapter(headerAdapter, projectsAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        projectsListViewModel.projectsLiveData.observe(this, {
            it?.let {
                projectsAdapter.submitList(it as MutableList<Project>)
                headerAdapter.updateProjectCount(it.size)
            }
        })

    }

    /* Opens ProjectDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(project: Project) {
        val intent = Intent(this, ProjectDetailActivity()::class.java)
        intent.putExtra(PROJECT_ID, project.id)
        startActivity(intent)
    }


}