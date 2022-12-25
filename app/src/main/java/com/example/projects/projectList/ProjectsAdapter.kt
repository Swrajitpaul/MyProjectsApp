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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projects.R
import com.example.projects.data.Project

class ProjectsAdapter(private val onClick: (Project) -> Unit) :
    ListAdapter<Project, ProjectsAdapter.ProjectViewHolder>(ProjectDiffCallback) {

    /* ViewHolder for Project, takes in the inflated view and the onClick behavior. */
    class ProjectViewHolder(itemView: View, val onClick: (Project) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val projectTextView: TextView = itemView.findViewById(R.id.project_text)
        private var currentProject: Project? = null

        init {
            itemView.setOnClickListener {
                currentProject?.let {
                    onClick(it)
                }
            }
        }

        /* Bind project name */
        fun bind(project: Project) {
            currentProject = project
            projectTextView.text = project.name
        }
    }

    /* Creates and inflates view and return ProjectViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.project_item, parent, false)
        return ProjectViewHolder(view, onClick)
    }

    /* Gets current project and uses it to bind view. */
    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = getItem(position)
        holder.bind(project)

    }
}

object ProjectDiffCallback : DiffUtil.ItemCallback<Project>() {
    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem.id == newItem.id
    }
}