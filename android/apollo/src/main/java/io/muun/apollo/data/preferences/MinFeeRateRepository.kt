package io.muun.apollo.data.preferences

import android.content.Context
import io.muun.apollo.data.preferences.adapter.DoublePreferenceAdapter
import io.muun.apollo.data.preferences.adapter.PublicKeyPreferenceAdapter
import io.muun.apollo.data.preferences.rx.Preference
import io.muun.common.crypto.hd.PublicKey
import javax.inject.Inject

open class MinFeeRateRepository @Inject constructor(
    context: Context,
    repositoryRegistry: RepositoryRegistry
) : BaseRepository(context, repositoryRegistry) {

    companion object {
        private const val KEY = "MIN_FEE_RATE"
    }

    private val preference: Preference<Double> = rxSharedPreferences.getObject(
        KEY,
        DoublePreferenceAdapter.INSTANCE
    )

    override fun getFileName(): String {
        return "min_fee_rate"
    }

    /**
     * Fetch an observable instance of the latest min fee rate in weight units.
     */
    fun fetch(): rx.Observable<Double> {
        return preference.asObservable()
    }

    /**
     * Fetch the current value of min fee rate in weight units.
     */
    fun fetchOne(): Double? {
        return preference.get()
    }

    open fun store(value: Double) {
        preference.set(value)
    }
}