package org.connect.chat.data.permission

import org.connect.chat.platform.PermissionManager
import org.connect.chat.domain.permission.PermissionType

class PermissionRepository(
    private val manager: PermissionManager
) {

    suspend fun check(permission: PermissionType) =
        manager.isPermissionGranted(permission)

    suspend fun request(permission: List<PermissionType>) =
        manager.requestPermission(permission)
}