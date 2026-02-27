package org.connect.chat.domain.permission

sealed class PermissionType {
    data object Camera : PermissionType()
    data object LocationForeground : PermissionType()
    data object LocationBackground : PermissionType()

    data object RecordAudio : PermissionType()
}