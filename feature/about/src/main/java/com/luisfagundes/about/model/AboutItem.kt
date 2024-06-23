package com.luisfagundes.about.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.ui.graphics.vector.ImageVector
import com.luisfagundes.about.R

data class AboutItem(
    val titleResId: Int,
    val descriptionResId: Int,
    val icon: ImageVector,
)

val aboutItemList = listOf(
    AboutItem(
        titleResId = R.string.feature_about_achievements_not_showing,
        icon = Icons.Default.QuestionMark,
        descriptionResId = R.string.feature_about_achievements_not_showing_description
    ),
    AboutItem(
        titleResId = R.string.feature_about_request_or_bug_report,
        descriptionResId = R.string.feature_about_request_or_bug_report_description,
        icon = Icons.Default.BugReport,
    )
)
