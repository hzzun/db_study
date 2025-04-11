package com.db_study.core.topic

import com.db_study.core.comment.Comment
import com.db_study.core.post.Post

data class Topic(
    val id: Long,
    val post: Post,
    val comments: List<Comment>
)
