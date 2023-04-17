package pt.brunoponte.aptoidestore.data.cache.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Class used to manipulate Shared Preferences.
 */
open class CachePreferencesHelper
@Inject
constructor(context: Context) {

    companion object {
        private const val PREF_PACKAGE_NAME = "pt.brunoponte.aptoidestore.cache.preferences"
        private const val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)

    var lastCacheTime: Long
        get() = preferences.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = preferences.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()
}