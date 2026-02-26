package org.connect.chat.platform

import org.connect.chat.domain.permission.PermissionStatus
import org.connect.chat.domain.permission.PermissionType

interface PermissionManager {

    suspend fun isPermissionGranted(permission: PermissionType): Boolean

    suspend fun requestPermission(permissions: List<PermissionType>): PermissionStatus
}

expect fun getPermissionManager(): PermissionManager