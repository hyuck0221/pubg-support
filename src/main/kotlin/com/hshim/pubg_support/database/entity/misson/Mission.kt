package com.hshim.pubg_support.database.entity.misson

import com.hshim.pubg_support.database.entity.base.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "mission")
class Chat (
    @Id
    @Column(nullable = false)
    var id: String = UUID.randomUUID().toString(),

    @Column(nullable = false)
    val content: String
) : BaseTimeEntity()