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

package com.example.projects.projectDetail

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.projects.R
import com.example.projects.projectList.PROJECT_ID
import com.example.projects.projectList.ProjectsListActivity

class ProjectDetailActivity : AppCompatActivity() {

    private val projectsDetailViewModel by viewModels<ProjectsDetailViewModel> {
        ProjectDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project_detail_activity)

        var currentProjectId: Long? = null

        /* Connect variables to UI elements. */
        val projectName: TextView = findViewById(R.id.project_detail_name)
        val projectDescription: TextView = findViewById(R.id.project_detail_description)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentProjectId = bundle.getLong(PROJECT_ID)
        }

        /* If currentProjectId is not null, get corresponding project and set name, image and
        description */
        currentProjectId?.let {
            val currentProject = projectsDetailViewModel.getProjectForId(it)
            projectName.text = currentProject?.name
            projectDescription.text = currentProject?.description

        }

        val textView: TextView = findViewById(R.id.project_detail_description)
        textView.movementMethod = ScrollingMovementMethod()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                intent = Intent(this, ProjectsListActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
}