package com.db_study.db.comment

import org.springframework.data.jpa.repository.JpaRepository

interface CommentJpaRepository : JpaRepository<CommentEntity, Long>