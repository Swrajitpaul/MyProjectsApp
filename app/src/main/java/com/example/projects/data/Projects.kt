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
import com.example.projects.R

/* Returns initial list of projects. */
fun projectList(resources: Resources): List<Project> {
    return listOf(
        Project(
            id = 1,
            name = resources.getString(R.string.project1_name),
            description = resources.getString(R.string.project1_description)
        ),
        Project(
            id = 2,
            name = resources.getString(R.string.project2_name),
            description = resources.getString(R.string.project2_description)
        ),
        Project(
            id = 3,
            name = resources.getString(R.string.project3_name),
            description = resources.getString(R.string.project3_description)
        ),
        Project(
            id = 4,
            name = resources.getString(R.string.project4_name),
            description = resources.getString(R.string.project4_description)
        ),
        Project(
            id = 5,
            name = resources.getString(R.string.project5_name),
            description = resources.getString(R.string.project5_description)
        ),
        Project(
            id = 6,
            name = resources.getString(R.string.project6_name),
            description = resources.getString(R.string.project6_description)
        ),
        Project(
            id = 7,
            name = resources.getString(R.string.project7_name),
            description = resources.getString(R.string.project7_description)
        ),
        Project(
            id = 8,
            name = resources.getString(R.string.project8_name),
            description = resources.getString(R.string.project8_description)
        ),
        Project(
            id = 9,
            name = resources.getString(R.string.project9_name),
            description = resources.getString(R.string.project9_description)
        ),
        Project(
            id = 10,
            name = resources.getString(R.string.project10_name),
            description = resources.getString(R.string.project10_description)
        ),
        Project(
            id = 11,
            name = resources.getString(R.string.project11_name),
            description = resources.getString(R.string.project11_description)
        )
    )
}