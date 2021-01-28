package io.muun.apollo.presentation

import io.muun.apollo.utils.RandomUser
import org.junit.Test

open class SettingsTests : BaseInstrumentationTest() {

    @Test
    fun test_01_a_user_can_change_password_with_old_password() {
        val user = RandomUser()
        val newPassword = "the new password"

        autoFlows.createRecoverableUser(user.pin, user.email, user.password)

        autoFlows.changePassword(user, newPassword)

        user.password = newPassword

        autoFlows.logOut()

        autoFlows.signIn(user.email, user.password, pin = user.pin)
    }

    @Test
    fun test_02_a_user_can_change_password_with_recovery_code() {
        val user = RandomUser()
        val newPassword = "the new password"

        autoFlows.createRecoverableUser(user.pin, user.email, user.password) // Needed to set up RC

        user.recoveryCode = autoFlows.setUpRecoveryCode()

        autoFlows.changePassword(user, newPassword)

        user.password = newPassword

        autoFlows.logOut()

        autoFlows.signIn(user.email, user.password, pin = user.pin)
    }
}
