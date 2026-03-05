package org.connect.chat.data

import org.connect.chat.domain.permission.PermissionType
import org.connect.chat.platform.PermissionManager

class PermissionRepository(
    private val manager: PermissionManager
) {

    suspend fun check(permission: PermissionType) =
        manager.isPermissionGranted(permission)

    suspend fun request(permission: List<PermissionType>) =
        manager.requestPermission(permission)
}