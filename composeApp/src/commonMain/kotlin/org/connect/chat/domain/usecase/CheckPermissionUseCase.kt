package org.connect.chat.domain.usecase

import org.connect.chat.data.permission.PermissionRepository
import org.connect.chat.domain.permission.PermissionType

class CheckPermissionUseCase(
    private val repo: PermissionRepository
) {
    suspend operator fun invoke(permission: PermissionType) =
        repo.check(permission)
}

class RequestPermissionUseCase(
    private val repo: PermissionRepository
) {
    suspend operator fun invoke(permission: List<PermissionType>) =
        repo.request(permission)
}